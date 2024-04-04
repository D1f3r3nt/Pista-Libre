struct GetClubResponse: Codable {
    let id: Int
    let name: String
    let location: String
    let photo: String?
    let courts: [GetClubCourtResponse]
}

struct GetClubCourtResponse: Codable {
    let id: Int
    let number: Int
    let indoor: Bool?
    let price: Float?
}
