typealias CourtsAPI = [CourtAPI]

struct CourtAPI: Codable {
    
    let id: Int?
    let club: Int
    let number: Int
    let indoor: Bool?
    let price: Float?
}
