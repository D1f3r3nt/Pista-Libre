import Fluent

final class SocialRepository {
    
    private let mapper: MapperDTOtoLocal = .init()
    
    func getAll(db: Database) async throws -> Socials {
        
        try await SocialDTO.query(on: db)
            .all()
            .map({ socialDto in
                mapper.social(socialDto: socialDto)
            })
        
    }
}
