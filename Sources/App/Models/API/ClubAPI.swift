typealias ClubsAPI = [ClubAPI]

struct ClubAPI: Codable {
    
    let id: Int?
    let name: String
    let location: String
    let photo: String?
}
