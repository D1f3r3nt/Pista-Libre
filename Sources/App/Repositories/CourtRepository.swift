import Fluent

final class CourtRepository {
    
    func deleteById(db: Database, idClub: Int) async throws {
        try await CourtDTO.query(on: db)
            .filter(\.$club == idClub)
            .delete()
    }
    
    func create(db: Database, court: CourtDTO) async throws {
        try await court.create(on: db)
    }
}
