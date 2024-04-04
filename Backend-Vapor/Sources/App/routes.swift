import Fluent
import Vapor

func routes(_ app: Application) throws {
    
    try app.register(collection: AuthController())
    try app.register(collection: ClubController())
    try app.register(collection: BookingController())
    try app.register(collection: ConfigController())
    try app.register(collection: SocialController())
    try app.register(collection: MessageController())
}
