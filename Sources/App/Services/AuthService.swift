import Vapor
import Fluent

class AuthService {
    
    private let clubRepostiory: ClubRepository = .init()
    private let userRepository: UserRepository = .init()
    
    func userSingUp(req: Request) async throws -> Response {
        
        do {
            let newUser: UserDTO = try req.content.decode(UserDTO.self)
            
            let existsEmail = try await clubRepostiory.getFromEmail(
                db: req.db,
                email: newUser.email
            )
            
            if (existsEmail != nil) {
                throw Abort(.badRequest, reason: "Email already exists as a Club")
            }
            
            try await userRepository.create(db: req.db, user: newUser)
            
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
            let newClub: ClubDTO = try req.content.decode(ClubDTO.self)
            
            
            let existsEmial = try await userRepository.getFromEmail(
                db: req.db,
                email: newClub.email
            )
            
            if (existsEmial != nil) {
                throw Abort(.badRequest, reason: "Email already exists as a User")
            }
            
            try await clubRepostiory.create(db: req.db, club: newClub)
            
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
        
        let user: User? = try await userRepository.getFromEmail(
            db: req.db,
            email: email
        )
        
        let club: Club? = try await clubRepostiory.getFromEmail(
            db: req.db,
            email: email
        )
        
        if (user == nil && club == nil) {
            
            throw Abort(.badRequest, reason: "Email dosen't exist")
            
        } else if (user != nil) {
            
            if (user!.password != password) {
                throw Abort(.badRequest, reason: "Incorrect password")
            }
            
            let jwtToken = Security(id: user!.id!, type: Role.USER.rawValue)
            
            return try Response(
                status: .ok,
                body: Response.Body(stringLiteral: req.jwt.sign(jwtToken))
            )
            
        } else {
            
            if (club!.password != password) {
                throw Abort(.badRequest, reason: "Incorrect password")
            }
            
            let jwtToken = Security(id: club!.id!, type: Role.CLUB.rawValue)
            
            return try Response(
                status: .ok,
                body: Response.Body(stringLiteral: req.jwt.sign(jwtToken))
            )
        }
    }
    
    func checkToken(req: Request) async throws -> Response {
        let sec: Security = try req.jwt.verify(as: Security.self)
        
        return try Response(
            status: .ok,
            body: Response.Body(data: JSONEncoder().encode(sec))
        )
    }
}
