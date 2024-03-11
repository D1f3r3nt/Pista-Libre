import Fluent
import Vapor

func routes(_ app: Application) throws {
    app.get { req async in
        "It works!"
    }

    app.get("hello") { req async -> String in
        "Hello, world!"
    }
    
    app.get("dev") { req async -> String in
        "VAMOS A EMPEZAR A DESARROLLAR PISTALIBRE"
    }

    try app.register(collection: TodoController())
}
