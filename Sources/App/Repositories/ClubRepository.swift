import Fluent
import Vapor

final class ClubRepository {
    
    private let mapper: MapperDTOtoLocal = .init()
    
    func create(
        db: Database,
        club: ClubDTO
    ) async throws {
        
        try await club.create(on: db)
    }
    
    func update(
        db:Database,
        id: Int,
        name: String,
        location: String,
        photo: String?
    ) async throws {
        
        try await ClubDTO.query(on: db)
            .set(\.$name, to: name)
            .set(\.$location, to: location)
            .set(\.$photo, to: photo)
            .filter(\.$id == id)
            .update()
    }
    
    func getAll(db: Database) async throws -> Clubs {
        
        let clubsDto = try await ClubDTO.query(on: db)
            .all()
        
        return clubsDto.map { clubDto in
            mapper.club(clubDto: clubDto)
        }
    }
    
    func getFromEmail(
        db: Database,
        email: String
    ) async throws -> Club? {
        
        let clubDto = try await ClubDTO.query(on: db)
            .filter(\.$email == email)
            .first()
        
        if (clubDto == nil) {
            return nil
        }
        
        return mapper.club(clubDto: clubDto!)
    }
}
