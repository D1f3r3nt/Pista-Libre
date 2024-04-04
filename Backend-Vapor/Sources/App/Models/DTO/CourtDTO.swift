import Fluent
import Vapor

typealias CourtsDTO = [CourtDTO]

final class CourtDTO: Model  {
    static let schema = "court"
    
    @ID(custom: "id")
    var id: Int?
    
    @Field(key: "club")
    var club: Int
    
    @Field(key: "number")
    var number: Int
    
    @Field(key: "indoor")
    var indoor: Bool?
    
    @Field(key: "price")
    var price: Float?
    
    init() { }
    
    init(
        id: Int?,
        club: Int,
        number: Int,
        indoor: Bool?,
        price: Float?
    ) {
        self.id = id
        self.club = club
        self.number = number
        self.indoor = indoor
        self.price = price
    }
}
