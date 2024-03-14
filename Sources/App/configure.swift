import NIOSSL
import Fluent
import FluentPostgresDriver
import Vapor

public func configure(_ app: Application) async throws {
    
    guard let _ = Environment.process.API_KEY else { fatalError("API_KEY not found") }
    guard let databaseURL = Environment.process.DATABASE_URL else { fatalError("DATABASE_URL not found") }
    
    app.databases.use(
        try DatabaseConfigurationFactory.postgres(url: databaseURL),
        as: .psql
    )

    // New migrations
    app.migrations.add(CreateDB())
    try await app.autoMigrate()
    
    // register routes
    try routes(app)
}
