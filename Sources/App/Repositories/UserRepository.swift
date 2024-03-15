import Fluent
import Vapor

final class UserRepository {
    
    static func create(db: Database, user: User) async throws {
        try await user.create(on: db)
    }
    
    static func getFromEmail(db: Database, email: String) async throws -> User? {
        return try await User.query(on: db)
            .filter(\.$email == email)
            .first()
    }
}
