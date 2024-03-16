typealias MessagesAPI = [MessageAPI]

struct MessageAPI: Codable {
    
    let id: Int?
    let owner: Int
    let date: String
    let text: String
    let targetUser: Int
}
