import Vapor
import Fluent

class SocialController: RouteCollection {
    
    private let socialService: SocialService = .init()
    
    func boot(routes: Vapor.RoutesBuilder) throws {
        let social = routes.grouped("social")
        
        // social/get
        social.get("get", use: socialService.getAll)
        
        // social/send
        social.post("send", use: socialService.send)
    }
    
    
}
