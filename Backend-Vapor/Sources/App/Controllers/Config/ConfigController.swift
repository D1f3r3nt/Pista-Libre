import Vapor

class ConfigController: RouteCollection {
    
    private let configService: ConfigService = .init()
    
    func boot(routes: Vapor.RoutesBuilder) throws {
        let config = routes.grouped("config")
        
        // config/club
        config.post("club", use: configService.club)
        
        // config/user
        config.post("user", use: configService.user)
        
        // config/user/info
        config.get("user","info", use: configService.infoUser)
        
        // config/user/info
        config.get("club","info", use: configService.infoClub)
    }
    
}
