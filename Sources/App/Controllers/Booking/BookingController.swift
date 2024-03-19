import Vapor
import Fluent

class BookingController: RouteCollection {
    
    final let bookingService: BookingService = .init()
    
    func boot(routes: Vapor.RoutesBuilder) throws {
        
        let bookings = routes.grouped("booking")
        
        // booking/inscribe
        bookings.post("inscribe", use: bookingService.inscribe)
        
    }
    
}
