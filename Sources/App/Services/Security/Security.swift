import JWT

struct Security: JWTPayload {
    
    enum CodingKeys: String, CodingKey {
        case id = "id"
        case type = "type"
    }

    var id: Int

    var type: String
    
    func verify(using signer: JWTKit.JWTSigner) throws {
        
    }
}
