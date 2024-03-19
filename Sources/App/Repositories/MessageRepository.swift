import Fluent

final class MessageRepository {
    
    func create(db: Database, messageDto: MessageDTO) async throws {
        try await messageDto.create(on: db)
    }
}
