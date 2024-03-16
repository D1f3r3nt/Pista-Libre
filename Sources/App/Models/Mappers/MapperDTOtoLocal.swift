final class MapperDTOtoLocal {
    
    func club(clubDto: ClubDTO) -> Club {
        Club(
            id: clubDto.id,
            name: clubDto.name,
            location: clubDto.location,
            photo: clubDto.photo,
            email: clubDto.email,
            password: clubDto.password
        )
    }
    
    func user(userDto: UserDTO) -> User {
        User(
            id: userDto.id,
            username: userDto.username,
            fullname: userDto.fullname,
            photo: userDto.photo,
            sidePlay: userDto.sidePlay,
            email: userDto.email,
            password: userDto.password
        )
    }
}
