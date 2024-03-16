import Foundation
import Vapor

struct ClubController: RouteCollection {
    
    let clubService: ClubService = .init()
    
    func boot(routes: Vapor.RoutesBuilder) throws {
        
        let club = routes.grouped("club")
        
        // club/all
        club.get("all", use: clubService.getAll)
    }
}
