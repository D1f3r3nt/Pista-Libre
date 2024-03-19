import Vapor
import Fluent

final class ConfigService {
    
    private let security: SecurityService = .init()
    private let clubRepository: ClubRepository = .init()
    private let courtRepository: CourtRepository = .init()
    
    func club(req: Request) async throws -> Response {
        let idClub = try security.checkAuthClub(req: req)
        
        guard let name: String = req.query["name"] else {
            throw Abort(.badRequest, reason: "Name missed")
        }
        
        guard let location: String = req.query["location"] else {
            throw Abort(.badRequest, reason: "Location missed")
        }
        
        let photo: String? = req.query["photo"]
        
        
        let courts = try req.content.decode([ConfigCourtRequest].self)
        
        try await clubRepository.update(
            db: req.db,
            id: idClub,
            name: name,
            location: location,
            photo: photo
        )
        
        try await courtRepository.deleteById(db: req.db, idClub: idClub)
        
        for court in courts {
            
            try await courtRepository.create(
                db: req.db,
                court: CourtDTO(
                    id: nil,
                    club: idClub,
                    number: court.number,
                    indoor: court.indoor,
                    price: court.price
                )
            )
        }
        
        return Response(status: .ok)
    }
}
