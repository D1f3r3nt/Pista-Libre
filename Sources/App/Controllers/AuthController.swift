import Foundation
import Vapor

struct AuthController: RouteCollection {
    
    let authService: AuthService = .init()
    
    func boot(routes: RoutesBuilder) throws {
        
        let auth = routes.grouped("auth")
        auth.post("sign-up", use: authService.singUp)
    }
}
