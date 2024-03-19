typealias Messages = [Message]

struct Message: Codable {
    
    let id: Int?
    let owner: Int
    let date: String
    let text: String
    let targetUser: Int
}
