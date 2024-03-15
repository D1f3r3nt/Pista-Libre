import Vapor
import Fluent

class AuthService {
    
    func userSingUp(req: Request) async throws -> Response {
        
        do {
            let newUser: User = try req.content.decode(User.self)
            
            let existsEmail = try await ClubRepository.getFromEmail(db: req.db, email: newUser.email)
            
            if (existsEmail != nil) {
                throw Abort(.badRequest, reason: "Email already exists as a Club")
            }
            
            try await UserRepository.create(db: req.db, user: newUser)
            
        } catch let error {
            throw Abort(
                .badRequest,
                reason: "\(error)"
            )
        }
        
        return Response(status: .created)
    }
    
    func clubSingUp(req: Request) async throws -> Response {
        
        do {
            let newClub: Club = try req.content.decode(Club.self)
            
            
            let existsEmial = try await UserRepository.getFromEmail(db: req.db, email: newClub.email)
            
            if (existsEmial != nil) {
                throw Abort(.badRequest, reason: "Email already exists as a User")
            }
            
            try await ClubRepository.create(db: req.db, club: newClub)
            
        } catch let error {
            throw Abort(
                .badRequest,
                reason: "\(error)"
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
        
        let user: User? = try await UserRepository.getFromEmail(
            db: req.db,
            email: email
        )
        
        let club: Club? = try await ClubRepository.getFromEmail(
            db: req.db,
            email: email
        )
        
        if (user === nil && club === nil) {
            
            throw Abort(.badRequest, reason: "Email dosen't exist")
            
        } else if (user !== nil) {
            
            if (user!.password != password) {
                throw Abort(.badRequest, reason: "Incorrect password")
            }
            
            return Response(
                status: .ok,
                body: Response.Body(stringLiteral: "user")
            )
            
        } else {
            
            if (club!.password != password) {
                throw Abort(.badRequest, reason: "Incorrect password")
            }
            
            return Response(
                status: .ok,
                body: Response.Body(stringLiteral: "club")
            )
        }
    }
}
