typealias Messages = [Message]

struct Message {
    
    let id: Int?
    let owner: Int
    let date: String
    let text: String
    let targetUser: String
}
