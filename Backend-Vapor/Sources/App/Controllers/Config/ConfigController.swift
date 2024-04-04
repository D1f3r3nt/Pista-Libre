import Vapor

class ConfigController: RouteCollection {
    
    private let configService: ConfigService = .init()
    
    func boot(routes: Vapor.RoutesBuilder) throws {
        let config = routes.grouped("config")
        
        // config/club
        config.post("club", use: configService.club)
        
        // config/user
        config.post("user", use: configService.user)
    }
    
}
