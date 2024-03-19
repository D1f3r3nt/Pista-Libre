import Vapor
import Fluent

final class SocialService {
    
    private let security: SecurityService = .init()
    private let socialRepository: SocialRepository = .init()
    
    func getAll(req: Request) async throws -> Response {
        let id = try security.check(req: req)
        
        let socials = try await socialRepository.getAll(db: req.db)
        
        return try Response(
            status: .ok,
            body: Response.Body(data: JSONEncoder().encode(socials))
        )
    }
}
