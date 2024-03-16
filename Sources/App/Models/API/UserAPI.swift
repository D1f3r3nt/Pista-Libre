typealias UsersAPI = [UserAPI]

struct UserAPI: Codable {
    
    let id: Int?
    let username: String
    let fullname: String
    let photo: String?
    let sidePlay: String?
}
