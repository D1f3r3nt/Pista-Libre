import Fluent

final class MessageRepository {
    
    func create(db: Database, messageDto: MessageDTO) async throws {
        try await messageDto.create(on: db)
    }
    
    func getChat(db: Database, user1: Int, user2: Int) async throws -> Messages {
        let sended = try await MessageDTO.query(on: db)
            .filter(\.$owner == user1)
            .filter(\.$targetUser == user2)
            .all()
        
        let recived = try await MessageDTO.query(on: db)
            .filter(\.$owner == user2)
            .filter(\.$targetUser == user1)
            .all()
        
        let allMessages = sended + recived
        
        return allMessages.map({ message in
                Message(
                    id: message.id,
                    owner: message.owner,
                    date: message.date,
                    text: message.text,
                    targetUser: message.targetUser
                )
            })
    }
    
    func getAllChatsIDs(db: Database, id: Int) async throws -> [Int] {
        
        let targetUser = try await MessageDTO.query(on: db)
            .filter(\.$targetUser == id)
            .all()
            .map({ message in
                message.owner
            })
        
        let owner = try await MessageDTO.query(on: db)
            .filter(\.$owner == id)
            .all()
            .map({ message in
                message.targetUser
            })
        
        return Array(Set(targetUser + owner))
    }
}
