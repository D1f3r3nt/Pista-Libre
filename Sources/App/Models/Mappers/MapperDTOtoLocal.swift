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
    
    func booking(bookingDto: BookingDTO) -> Booking {
        Booking(
            id: bookingDto.id,
            court: bookingDto.court,
            date: bookingDto.date,
            p1: bookingDto.p1,
            p2: bookingDto.p2,
            p3: bookingDto.p3,
            p4: bookingDto.p4
        )
    }
    
    func social(socialDto: SocialDTO) -> Social {
        Social(
            id: socialDto.id,
            owner: socialDto.owner,
            date: socialDto.date,
            text: socialDto.text,
            photo: socialDto.photo
        )
    }
}
