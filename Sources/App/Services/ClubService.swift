import Vapor
import Fluent

class ClubService {
    
    private let clubRepository: ClubRepository = .init()
    private let mapper: MapperLocalToAPI = .init()
    
    func getAll(req: Request) async throws -> Response {
        try checkAuthUser(req: req)
        
        let clubs: Clubs = try await clubRepository.getAll(db: req.db)
        
        let clubsApi = clubs.map { club in
            mapper.club(club: club)
        }
        
        return try Response(
            status: .ok,
            body: Response.Body(data: JSONEncoder().encode(clubsApi))
        )
    }
    
    private func checkAuthUser(req: Request) throws {
        let sec: Security = try req.jwt.verify(as: Security.self)
        
        if (sec.type != Role.USER.rawValue) {
            throw Abort(.unauthorized, reason: "Clubs cannot do this action")
        }
    }
}
