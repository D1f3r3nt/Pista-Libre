typealias Clubs = [Club]

struct Club: Codable {
     
    let id: Int?
    let name: String
    let location: String
    let photo: String?
    let email: String
    let password: String
}
