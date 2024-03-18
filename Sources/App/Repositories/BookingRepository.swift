import Vapor
import Fluent

final class BookingRepository {
    
    private let mapper: MapperDTOtoLocal = .init()
    
    func getById(db: Database, id: Int) async throws -> Booking? {
        
        guard let bookingDto = try await BookingDTO.query(on: db)
            .filter(\.$id == id)
            .first() else {
            return nil
        }
        
        return mapper.booking(bookingDto: bookingDto)
    }
    
    func getByCourtDate(db: Database, court: Int, date: String) async throws -> Booking? {
        
        guard let bookingDto = try await BookingDTO.query(on: db)
            .filter(\.$court == court)
            .filter(\.$date == date)
            .first() else {
            return nil
        }
        
        return mapper.booking(bookingDto: bookingDto)
    }
    
    func createBooking(db: Database, booking: BookingDTO) async throws {
        
        try await booking.create(on: db)
    }
    
    func updateBookingP2(db: Database, id: Int, p2: Int) async throws {
        
        try await BookingDTO.query(on: db)
            .set(\.$p2, to: p2)
            .filter(\.$id == id)
            .update()
    }
    
    func updateBookingP3(db: Database, id: Int, p3: Int) async throws {
        
        try await BookingDTO.query(on: db)
            .set(\.$p3, to: p3)
            .filter(\.$id == id)
            .update()
    }
    
    func updateBookingP4(db: Database, id: Int, p4: Int) async throws {
        
        try await BookingDTO.query(on: db)
            .set(\.$p4, to: p4)
            .filter(\.$id == id)
            .update()
    }
}
