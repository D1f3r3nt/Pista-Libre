final class MapperLocalToDTO {
    
    func booking(booking: Booking) -> BookingDTO {
        BookingDTO(
            id: booking.id,
            court: booking.court,
            date: booking.date,
            p1: booking.p1,
            p2: booking.p2,
            p3: booking.p3,
            p4: booking.p4
        )
    }
}
