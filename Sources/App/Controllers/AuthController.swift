import Foundation
import Vapor

struct AuthController: RouteCollection {
    
    let authService: AuthService = .init()
    
    func boot(routes: RoutesBuilder) throws {
        
        let auth = routes.grouped("auth")
        
        auth.group("user") { userAuth in
            
            // auth/user/sign-up
            userAuth.post("sign-up", use: authService.userSingUp)
        }
        
        auth.group("club") { clubAuth in
            
            // auth/club/sign-up
            clubAuth.post("sign-up", use: authService.clubSingUp)
        }
        
        // auth/log-in
        auth.get("log-in", use: authService.logIn)
        
        // auth/checkToken
        auth.get("checkToken", use: authService.checkToken)
    }
}
