import Vapor
import Fluent

class ClubService {
    
    private let clubRepository: ClubRepository = .init()
    private let security: SecurityService = .init()
    
    func getAll(req: Request) async throws -> Response {
        let _ = try security.checkAuthUser(req: req)
        
        let clubs: Clubs = try await clubRepository.getAll(db: req.db)
        
        let clubsApi = clubs.map { club in
            AllClubRespose(
                id: club.id ?? 0,
                name: club.name,
                location: club.location,
                photo: club.photo
            )
        }
        
        return try Response(
            status: .ok,
            body: Response.Body(data: JSONEncoder().encode(clubsApi))
        )
    }
    
}
