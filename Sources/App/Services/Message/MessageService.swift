import Vapor
import Fluent

final class MessageService {
    
    private let security: SecurityService = .init()
    private let messageRepository: MessageRepository = .init()
    
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
}
