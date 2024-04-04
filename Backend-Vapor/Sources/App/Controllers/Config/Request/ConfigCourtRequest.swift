struct ConfigCourtRequest: Decodable {
    var number: Int
    var indoor: Bool?
    var price: Float?
}
