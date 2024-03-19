import Vapor

final class BookingService {
    
    private let security: SecurityService = .init()
    private let bookingRepository: BookingRepository = .init()
    
    func inscribe(req: Request) async throws -> Response {
        let currentUserId = try security.checkAuthUser(req: req)
        
        guard let idCourt: Int = Int(req.query["court"] ?? "") else {
            throw Abort(.badRequest, reason: "Court missed")
        }
        
        guard let date: String = req.query["date"] else {
            throw Abort(.badRequest, reason: "Date missed")
        }
        
        let booking = try await bookingRepository.getByCourtDate(
            db: req.db,
            court: idCourt,
            date: date
        )
        
        if (booking == nil) {
            let newBooking: BookingDTO = BookingDTO(
                id: nil,
                court: idCourt,
                date: date,
                p1: currentUserId,
                p2: nil,
                p3: nil,
                p4: nil
            )
            
            try await bookingRepository.createBooking(
                db: req.db,
                booking: newBooking
            )
            
            return Response(status: .ok)
            
        } else if (booking!.p2 == nil) {
            try await bookingRepository.updateBookingP2(
                db: req.db,
                id: booking!.id!,
                p2: currentUserId
            )
        } else if (booking!.p3 == nil) {
            try await bookingRepository.updateBookingP3(
                db: req.db,
                id: booking!.id!,
                p3: currentUserId
            )
        } else if (booking!.p4 == nil) {
            try await bookingRepository.updateBookingP4(
                db: req.db,
                id: booking!.id!,
                p4: currentUserId
            )
        } else {
            throw Abort(.badRequest, reason: "Pista llena")
        }
        
        return Response(status: .ok)
    }
}
