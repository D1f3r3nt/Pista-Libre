import Fluent

final class CourtRepository {
    
    private let mapper: MapperDTOtoLocal = .init()
    
    func deleteById(db: Database, idClub: Int) async throws {
        try await CourtDTO.query(on: db)
            .filter(\.$club == idClub)
            .delete()
    }
    
    func getByClub(db: Database, idClub: Int) async throws -> Courts {
        let courtsDto = try await CourtDTO.query(on: db)
            .filter(\.$club == idClub)
            .all()
        
        return courtsDto.map { courtDto in
            mapper.court(courtDto: courtDto)
        }
    }
    
    func create(db: Database, court: CourtDTO) async throws {
        try await court.create(on: db)
    }
}
