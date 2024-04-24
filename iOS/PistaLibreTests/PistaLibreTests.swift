//
//  PistaLibreTests.swift
//  PistaLibreTests
//
//  Created by Pablo Marín Gallardo on 9/4/24.
//

import XCTest
@testable import PistaLibre

final class PistaLibreTests: XCTestCase {

    func testPlayerInitialization() {
        // Given or Arrange
        let username = "TestUsername"
        let fullname = "Test Full Name"
        let email = "test@email.com"
        let password = "testpassword1234"
        
        // When or Act
        let player = Player(username: username, fullname: fullname, email: email, password: password)
        
        // Then or Assert
        XCTAssertEqual(player.username, username, "Username should be equal to TestUsername")
        XCTAssertEqual(player.fullname, fullname, "Fullname should be equal to Test Full Name")
        XCTAssertEqual(player.email, email, "Email should be equal to test@email.com")
        XCTAssertEqual(player.password, password, "Password should be equal to testpassword1234")
    }
    
    func testClubInitialization() {
        // Given or Arrange
        let name = "Test Name"
        let location = "Test Location"
        let email = "test@email.com"
        let password = "testpassword1234"
        
        // When or Act
        let club = Club(name: name, location: location, email: email, password: password)
        
        // Then or Assert
        XCTAssertEqual(club.name, name, "Nameame should be equal to Test Name")
        XCTAssertEqual(club.location, location, "Location should be equal to Test Location")
        XCTAssertEqual(club.email, email, "Email should be equal to test@email.com")
        XCTAssertEqual(club.password, password, "Password should be equal to testpassword1234")
    }
    
    func testTokenInitialization() {
        let type = "player"
        let id = 10
        
        let checkToken = Token(type: type, id: id)
        
        XCTAssertEqual(checkToken.type, type, "Type should be equal to player")
        XCTAssertEqual(checkToken.id, id, "Id should be equal to 10")
    }
}
