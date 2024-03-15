import Fluent
import Vapor

final class ClubRepository {
    
    static func create(db: Database, club: Club) async throws {
        try await club.create(on: db)
    }
    
    static func getFromEmail(db: Database, email: String) async throws -> Club? {
        return try await Club.query(on: db)
            .filter(\.$email == email)
            .first()
    }
}
