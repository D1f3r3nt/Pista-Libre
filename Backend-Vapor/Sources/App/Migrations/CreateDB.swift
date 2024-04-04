import Fluent

struct CreateDB: AsyncMigration {
    func prepare(on database: Database) async throws {
        
        // Create User
        try await database.schema("user")
            .field("id", .int, .identifier(auto: true))
            .field("username", .string, .required)
            .field("fullname", .string, .required)
            .field("photo", .string)
            .field("side_play", .string)
            .field("email", .string, .required)
            .field("password", .string, .required)
            .unique(on: "username")
            .unique(on: "email")
            .create()
        
        // Create Club
        try await database.schema("club")
            .field("id", .int, .identifier(auto: true))
            .field("name", .string, .required)
            .field("location", .string, .required)
            .field("photo", .string)
            .field("email", .string, .required)
            .field("password", .string, .required)
            .unique(on: "email")
            .create()
        
        // Create Court
        try await database.schema("court")
            .field("id", .int, .identifier(auto: true))
            .field("club", .int, .required, .references("club", "id", onDelete: .cascade))
            .field("number", .int, .required)
            .field("indoor", .bool)
            .field("price", .float)
            .unique(on: "number", "club")
            .create()
        
        // Create Booking
        try await database.schema("booking")
            .field("id", .int, .identifier(auto: true))
            .field("court", .int, .required, .references("court", "id", onDelete: .cascade))
            .field("date", .string, .required)
            .field("p1", .int, .required, .references("user", "id", onDelete: .cascade))
            .field("p2", .int, .references("user", "id", onDelete: .setNull))
            .field("p3", .int, .references("user", "id", onDelete: .setNull))
            .field("p4", .int, .references("user", "id", onDelete: .setNull))
            .unique(on: "date", "court")
            .create()
        
        // Create Social
        try await database.schema("social")
            .field("id", .int, .identifier(auto: true))
            .field("owner", .int, .required, .references("user", "id", onDelete: .cascade))
            .field("date", .string, .required)
            .field("text", .string, .required)
            .field("photo", .string)
            .create()
        
        // Create Message
        try await database.schema("message")
            .field("id", .int, .identifier(auto: true))
            .field("owner", .int, .required, .references("user", "id"))
            .field("date", .string, .required)
            .field("text", .string, .required)
            .field("target_user", .int, .required, .references("user", "id"))
            .create()
    }

    func revert(on database: Database) async throws {
        try await database.schema("user").delete()
        try await database.schema("court").delete()
        try await database.schema("club").delete()
        try await database.schema("social").delete()
        try await database.schema("booking").delete()
        try await database.schema("message").delete()
    }
}
