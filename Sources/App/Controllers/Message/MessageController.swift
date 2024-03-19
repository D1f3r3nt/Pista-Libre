import Vapor

class MessageController: RouteCollection {
    
    private let messageService: MessageService = .init()
    
    func boot(routes: Vapor.RoutesBuilder) throws {
        let message = routes.grouped("message")
        
        // message/send/:idReciver
        message.post("send", ":idReciver", use: messageService.send)
        
        // message/get/:idReciver
        
        // message/get/chats
    }
    
}
