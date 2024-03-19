typealias Users = [User]

struct User: Codable {
    
    let id: Int?
    let username: String
    let fullname: String
    let photo: String?
    let sidePlay: String?
    let email: String
    let password: String
}
