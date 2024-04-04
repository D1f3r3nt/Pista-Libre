import Vapor
import Fluent

final class SocialService {
    
    private let security: SecurityService = .init()
    private let socialRepository: SocialRepository = .init()
    
    func getAll(req: Request) async throws -> Response {
        let _ = try security.check(req: req)
        
        let socials = try await socialRepository.getAll(db: req.db)
        
        return try Response(
            status: .ok,
            body: Response.Body(data: JSONEncoder().encode(socials))
        )
    }
    
    func send(req: Request) async throws -> Response {
        let idUser = try security.checkAuthUser(req: req)
        
        let newSocial = try req.content.decode(SendSocialRequest.self)
        
        try await socialRepository.create(
            db: req.db,
            social: SocialDTO(
                id: nil,
                owner: idUser,
                date: OurDate.getCurrentDate(),
                text: newSocial.text,
                photo: newSocial.photo
            )
        )
        
        return Response(
            status: .ok
        )
    }
}
