import Fluent
import Vapor

final class UserRepository {
    
    private let mapper: MapperDTOtoLocal = .init()
    
    func create(db: Database, user: UserDTO) async throws {
        try await user.create(on: db)
    }
    
    func getFromEmail(db: Database, email: String) async throws -> User? {
        let userDto = try await UserDTO.query(on: db)
            .filter(\.$email == email)
            .first()
        
        if (userDto == nil) {
            return nil
        }
        
        return mapper.user(userDto: userDto!)
    }
}
