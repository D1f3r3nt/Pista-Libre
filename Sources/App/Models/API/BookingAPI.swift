typealias BookingsAPI = [BookingAPI]

struct BookingAPI: Codable {
    
    let id: Int?
    let court: Int
    let date: String
    let p1: Int
    let p2: Int?
    let p3: Int?
    let p4: Int?
}
