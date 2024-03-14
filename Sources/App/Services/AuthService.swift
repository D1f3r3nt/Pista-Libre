import Vapor
import Fluent

class AuthService {
    
    func singUp(req: Request) async throws -> Response {
        
        do {
            let newUser: User = try req.content.decode(User.self)
            
            try await newUser.create(on: req.db)
            
        } catch let error {
            return Response(
                status: .badRequest,
                body: Response.Body(stringLiteral: "Service Exception: \(error)")
            )
        }
        
        return Response(status: .created)
    }
    
}
