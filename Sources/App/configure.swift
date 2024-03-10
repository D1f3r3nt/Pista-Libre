import NIOSSL
import Fluent
import FluentPostgresDriver
import Vapor

// configures your application
public func configure(_ app: Application) async throws {
    // uncomment to serve files from /Public folder
    // app.middleware.use(FileMiddleware(publicDirectory: app.directory.publicDirectory))
    
    guard let _ = Environment.process.API_KEY else { fatalError("API_KEY not found") }
    guard let databaseURL = Environment.process.DATABASE_URL else { fatalError("DATABASE_URL not found") }
    
    app.databases.use(
        try DatabaseConfigurationFactory.postgres(url: databaseURL),
        as: .psql
    )

    app.migrations.add(CreateTodo())
    // register routes
    try routes(app)
}
