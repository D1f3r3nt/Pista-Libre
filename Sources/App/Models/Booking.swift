import Fluent
import Vapor

typealias Bookings = [Booking]

final class Booking: Model  {
    static let schema = "booking"
    
    @ID(custom: "id")
    var id: Int?
    
    @Field(key: "court")
    var court: Int
    
    @Field(key: "date")
    var date: String
    
    @Field(key: "p1")
    var p1: Int
    
    @Field(key: "p2")
    var p2: Int?
    
    @Field(key: "p3")
    var p3: Int?
    
    @Field(key: "p4")
    var p4: Int?
    
    init() { }
    
    init(
        id: Int?,
        court: Int,
        date: String,
        p1: Int,
        p2: Int?,
        p3: Int?,
        p4: Int?
    ) {
        self.id = id
        self.court = court
        self.date = date
        self.p1 = p1
        self.p2 = p2
        self.p3 = p3
        self.p4 = p4
    }
}
