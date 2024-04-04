import Vapor
import Fluent

final class MessageService {
    
    private let security: SecurityService = .init()
    private let messageRepository: MessageRepository = .init()
    private let userRepository: UserRepository = .init()
    
    func send(req: Request) async throws -> Response {
        let idUser = try security.checkAuthUser(req: req)
        
        guard let targetUser: Int = Int(req.parameters.get("idReciver") ?? "-") else {
            throw Abort(.badRequest, reason: "ID target user missing")
        }
        
        let request = try req.content.decode(SendMessageRequest.self)
        
        try await messageRepository.create(
            db: req.db,
            messageDto: MessageDTO(
                id: nil,
                owner: idUser,
                date: OurDate.getCurrentDate(),
                text: request.text,
                targetUser: targetUser
            )
        )
        
        return Response(status: .ok)
    }
    
    func getChat(req: Request) async throws -> Response {
        let idUser = try security.checkAuthUser(req: req)
        
        guard let targetUser: Int = Int(req.parameters.get("idReciver") ?? "-") else {
            throw Abort(.badRequest, reason: "ID target user missing")
        }
        
        var allMessages = try await messageRepository.getChat(db: req.db, user1: idUser, user2: targetUser)
        
        allMessages.sort { message1, message2 in
            OurDate.sortDates(message1.date, message2.date)
        }
        
        return try Response(
            status: .ok,
            body: Response.Body(data: JSONEncoder().encode(allMessages))
        )
    }
    
    func getAllChats(req: Request) async throws -> Response {
        let idUser = try security.checkAuthUser(req: req)
        
        let allChatsIds = try await messageRepository.getAllChatsIDs(db: req.db, id: idUser)
        
        var allChats: Users = []
        
        for id in allChatsIds {
            let user = try await userRepository.getById(db: req.db, id: id)
            
            if (user != nil) {
                allChats.append(user!)
            }
        }
        
        let response = allChats.map { user in
            AllChatsResponse(
                id: user.id ?? 0,
                username: user.username,
                fullname: user.fullname,
                photo: user.photo,
                sidePlay: user.sidePlay
            )
        }
        
        return try Response(
            status: .ok,
            body: Response.Body(data: JSONEncoder().encode(response))
        )
    }
}
