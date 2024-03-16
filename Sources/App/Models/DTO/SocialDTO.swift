import Fluent
import Vapor

typealias SocialsDTO = [SocialDTO]

final class SocialDTO: Model  {
    static let schema = "social"
    
    @ID(custom: "id")
    var id: Int?
    
    @Field(key: "owner")
    var owner: Int
    
    @Field(key: "date")
    var date: String
    
    @Field(key: "text")
    var text: String
    
    @Field(key: "photo")
    var photo: String?
    
    init() { }
    
    init(
        id: Int?,
        owner: Int,
        date: String,
        text: String,
        photo: String?
    ) {
        self.id = id
        self.owner = owner
        self.date = date
        self.photo = photo
        self.text = text
    }
}
