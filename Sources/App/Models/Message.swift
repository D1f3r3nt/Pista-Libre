import Fluent
import Vapor

typealias Messages = [Message]

final class Message: Model  {
    static let schema = "message"
    
    @ID(custom: "id")
    var id: Int?
    
    @Field(key: "owner")
    var owner: Int
    
    @Field(key: "date")
    var date: String
    
    @Field(key: "text")
    var text: String
    
    @Field(key: "target_user")
    var targetUser: Int
    
    init() { }
    
    init(
        id: Int?,
        owner: Int,
        date: String,
        text: String,
        targetUser: Int
    ) {
        self.id = id
        self.owner = owner
        self.date = date
        self.targetUser = targetUser
        self.text = text
    }
}
