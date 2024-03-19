import Vapor

final class SecurityService {
    
    func checkAuthUser(req: Request) throws -> Int {
        let sec: Security = try req.jwt.verify(as: Security.self)
        
        if (sec.type != Role.USER.rawValue) {
            throw Abort(.unauthorized, reason: "Clubs cannot do this action")
        }
        
        return sec.id
    }
    
    func checkAuthClub(req: Request) throws -> Int {
        let sec: Security = try req.jwt.verify(as: Security.self)
        
        if (sec.type != Role.CLUB.rawValue) {
            throw Abort(.unauthorized, reason: "Users cannot do this action")
        }
        
        return sec.id
    }
    
    func check(req: Request) throws -> Int {
        let sec = try req.jwt.verify(as: Security.self)
        
        return sec.id
    }
}
