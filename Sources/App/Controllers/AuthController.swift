import Foundation
import Vapor

struct AuthController: RouteCollection {
    
    let authService: AuthService = .init()
    
    func boot(routes: RoutesBuilder) throws {
        
        let auth = routes.grouped("auth")
        
        auth.group("user") { userAuth in
            userAuth.post("sign-up", use: authService.userSingUp)
        }
        
        auth.group("club") { clubAuth in
            clubAuth.post("sign-up", use: authService.clubSingUp)
        }
        
        auth.get("log-in", use: authService.logIn)
    }
}
