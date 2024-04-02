# Pista Libre Vapor

This project is a backend created with Swift Vapor technology, which allows us to create a backend with the Swift programming language.

This project has different endpoints to manage the free court application, an application to book paddle courts, 
to save all this information the server has the implementation of Fluent technology which allows to connect the backend with a database, in this case PostgreSQL.

To protect the backend from possible requests from unauthorized persons, JWT authentication has been implemented,
which saves the identifier of the user who has made the request so that this information does not have to be passed.

Finally, a Dockerfile has been created to be able to deploy it on the Fly.io server along with its database and thus 
be able to have two working environments with two different databases, one for local and one for dev.
