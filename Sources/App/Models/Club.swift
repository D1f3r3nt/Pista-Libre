import Fluent
import Vapor

typealias Clubs = [Club]

final class Club: Model  {
    static let schema = "club"
    
    @ID(custom: "id")
    var id: Int?
    
    @Field(key: "name")
    var name: String
    
    @Field(key: "location")
    var location: String
    
    @Field(key: "photo")
    var photo: String?
    
    @Field(key: "email")
    var email: String
    
    @Field(key: "password")
    var password: String
    
    init() { }
    
    init(
        id: Int?,
        name: String,
        location: String,
        photo: String?,
        email: String,
        password: String
    ) {
        self.id = id
        self.name = name
        self.location = location
        self.photo = photo
        self.email = email
        self.password = password
    }
}
