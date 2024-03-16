import Fluent
import Vapor

final class ClubRepository {
    
    private let mapper: MapperDTOtoLocal = .init()
    
    func create(db: Database, club: ClubDTO) async throws {
        try await club.create(on: db)
    }
    
    func getAll(db: Database) async throws -> Clubs {
        let clubsDto = try await ClubDTO.query(on: db)
            .all()
        
        return clubsDto.map { clubDto in
            mapper.club(clubDto: clubDto)
        }
    }
    
    func getFromEmail(db: Database, email: String) async throws -> Club? {
        let clubDto = try await ClubDTO.query(on: db)
            .filter(\.$email == email)
            .first()
        
        if (clubDto == nil) {
            return nil
        }
        
        return mapper.club(clubDto: clubDto!)
    }
}
