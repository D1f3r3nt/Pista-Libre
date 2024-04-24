//
//  ModelTests.swift
//  PistaLibreTests
//
//  Created by Pablo Marín Gallardo on 22/4/24.
//

import XCTest
@testable import PistaLibre

final class ModelTests: XCTestCase {
    
    func testPlayerModel() {
            // Creo una instancia del modelo Player
            let player = Player(username: "test1234", fullname: "Pablo Test", email: "pablo@test.com", password: "testPassword1234")

            // Codifico la instancia de Player a JSON
            let encoder = JSONEncoder()
            var data: Data?
            do {
                data = try encoder.encode(player)
            } catch {
                XCTFail("Error al codificar el modelo Player: \(error)")
            }

            // Decodifico el JSON a una instancia de Player
            let decoder = JSONDecoder()
            var decodedPlayer: Player?
            if let jsonData = data {
                do {
                    decodedPlayer = try decoder.decode(Player.self, from: jsonData)
                } catch {
                    XCTFail("Error al decodificar el modelo Player: \(error)")
                }
            }

            // Verifico que la instancia decodificada corresponde a la original
            XCTAssertEqual(player.username, decodedPlayer?.username)
            XCTAssertEqual(player.fullname, decodedPlayer?.fullname)
            XCTAssertEqual(player.email, decodedPlayer?.email)
            XCTAssertEqual(player.password, decodedPlayer?.password)
        }
    
    func testClubModel() {
        
        let club = Club(name: "test1234", location: "C/ Test 8", email: "club@test.com", password: "test1234")
        
        let encoder = JSONEncoder()
        var data: Data?
        
        do {
            data = try encoder.encode(club)
        } catch {
            XCTFail("Error al codificar el modelo Club: \(error)")
        }
        
        let decoder = JSONDecoder()
        var decodedClub: Club?
        
        if let jsonData = data {
            do {
                decodedClub = try decoder.decode(Club.self, from: jsonData)
            } catch {
                XCTFail("Error al decodificar el modelo Club: \(error)")
            }
        }
        
        XCTAssertEqual(club.name, decodedClub?.name)
        XCTAssertEqual(club.location, decodedClub?.location)
        XCTAssertEqual(club.email, decodedClub?.email)
        XCTAssertEqual(club.password, decodedClub?.password)
    }
    
    func testTokenModel() {
        
        let token = Token(type: "player", id: 1)
        
        let encoder = JSONEncoder()
        var data: Data?
        
        do {
            data = try encoder.encode(token)
        } catch {
            XCTFail("Error al codificar el modelo Token: \(error)")
        }
        
        let decoder = JSONDecoder()
        var decodedToken: Token?
        
        if let jsonData = data {
            do {
                decodedToken = try decoder.decode(Token.self, from: jsonData)
            } catch {
                XCTFail("Error al decodificar el modelo Token: \(error)")
            }
        }
        
        XCTAssertEqual(token.type, decodedToken?.type)
        XCTAssertEqual(token.id, decodedToken?.id)
    }
}
