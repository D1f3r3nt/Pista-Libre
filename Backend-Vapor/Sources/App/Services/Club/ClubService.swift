import Vapor
import Fluent

class ClubService {
    
    private let clubRepository: ClubRepository = .init()
    private let courtRepository: CourtRepository = .init()
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
    
    func getOne(req: Request) async throws -> Response {
        let _ = try security.checkAuthUser(req: req)
        
        guard let idClub: Int = Int(req.parameters.get("id") ?? "-") else {
            throw Abort(.badRequest, reason: "ID's club missing")
        }
        
        guard let club: Club = try await clubRepository.getById(db: req.db, id: idClub) else {
            throw Abort(.badRequest, reason: "ID's club dosen't exist")
        }
        
        let courts = try await courtRepository.getByClub(db: req.db, idClub: idClub)
        
        let courtsApi = courts.map { court in
            GetClubCourtResponse(
                id: court.id ?? 0,
                number: court.number,
                indoor: court.indoor,
                price: court.price
            )
        }
        
        let response = GetClubResponse(
            id: club.id ?? 0,
            name: club.name,
            location: club.location,
            photo: club.photo,
            courts: courtsApi
        )
        
        return try Response(
            status: .ok,
            body: Response.Body(data: JSONEncoder().encode(response))
        )
    }
    
}
