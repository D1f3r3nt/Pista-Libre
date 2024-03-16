final class MapperLocalToAPI {
    
    func club(club: Club) -> ClubAPI {
        ClubAPI(
            id: club.id,
            name: club.name,
            location: club.location,
            photo: club.photo
        )
    }
    
    func user(user: User) -> UserAPI {
        UserAPI(
            id: user.id,
            username: user.username,
            fullname: user.fullname,
            photo: user.photo,
            sidePlay: user.sidePlay
        )
    }
}
