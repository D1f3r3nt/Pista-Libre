import Vapor
import Fluent

class AuthService {
    
    func singUp(req: Request) async throws -> Response {
        
        do {
            let newUser: User = try req.content.decode(User.self)
            
            try await newUser.create(on: req.db)
            
        } catch let error {
            throw Abort(
                .badRequest,
                reason: "Service Exception: \(error)"
            )
        }
        
        return Response(status: .created)
    }
    
    func logIn(req: Request) async throws -> Response {
        
        guard let email: String = req.query["email"] else {
            throw Abort(.badRequest, reason: "Email missed")
        }
        
        guard let password: String = req.query["password"] else {
            throw Abort(.badRequest, reason: "Password missed")
        }
        
        guard let user: User = try await User.query(on: req.db)
            .filter(\.$email == email)
            .first()
        else {
            throw Abort(.badRequest, reason: "Email dosen't exist")
        }
        
        if (user.password != password) {
            throw Abort(.badRequest, reason: "Incorrect password")
        }
        
        return Response(status: .ok)
    }
}
