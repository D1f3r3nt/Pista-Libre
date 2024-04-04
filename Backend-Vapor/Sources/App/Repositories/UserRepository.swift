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
    
    func getById(db: Database, id: Int) async throws -> User? {
        let userDto = try await UserDTO.query(on: db)
            .filter(\.$id == id)
            .first()
        
        if (userDto == nil) {
            return nil
        }
        
        return mapper.user(userDto: userDto!)
    }
    
    func update(db: Database, user: UserDTO) async throws {
        try await UserDTO.query(on: db)
            .set(\.$username, to: user.username)
            .set(\.$fullname, to: user.fullname)
            .set(\.$photo, to: user.photo)
            .set(\.$sidePlay, to: user.sidePlay)
            .filter(\.$id == user.id!)
            .update()
    }
}
