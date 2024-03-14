import Fluent
import Vapor

typealias Users = [User]

final class User: Model  {
    static let schema = "user"
    
    @ID(custom: "id")
    var id: Int?
    
    @Field(key: "username")
    var username: String
    
    @Field(key: "fullname")
    var fullname: String
    
    @Field(key: "photo")
    var photo: String?
    
    @Field(key: "side_play")
    var sidePlay: String?
    
    @Field(key: "email")
    var email: String
    
    @Field(key: "password")
    var password: String
    
    init() { }
    
    init(
        id: Int?,
        username: String,
        fullname: String,
        photo: String?,
        sidePlay: String?,
        email: String,
        password: String
    ) {
        self.id = id
        self.username = username
        self.fullname = fullname
        self.photo = photo
        self.sidePlay = sidePlay
        self.email = email
        self.password = password
    }
}
