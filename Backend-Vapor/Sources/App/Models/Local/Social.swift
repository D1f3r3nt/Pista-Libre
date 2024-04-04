typealias Socials = [Social]

struct Social: Codable {
    
    let id: Int?
    let owner: Int
    let date: String
    let text: String
    let photo: String?
}
