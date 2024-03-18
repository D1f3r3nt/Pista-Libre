import Vapor
import Fluent

class ClubService {
    
    private let clubRepository: ClubRepository = .init()
    private let security: SecurityService = .init()
    private let mapper: MapperLocalToAPI = .init()
    
    func getAll(req: Request) async throws -> Response {
        try security.checkAuthUser(req: req)
        
        let clubs: Clubs = try await clubRepository.getAll(db: req.db)
        
        let clubsApi = clubs.map { club in
            mapper.club(club: club)
        }
        
        return try Response(
            status: .ok,
            body: Response.Body(data: JSONEncoder().encode(clubsApi))
        )
    }
    
}
