//
//  AuthViewInteractor.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 11/4/24.
//

import Foundation
import Combine

protocol AuthViewInteractorProtocol: AnyObject {
    func registerPlayer(player: Player) -> AnyPublisher<Void, Error>
    func registerClub(club: Club) -> AnyPublisher<Void, Error>
    func login(email: String, password: String) -> AnyPublisher<String, Error>
    func checkToken() -> AnyPublisher<Token, Error>
}

final class AuthViewInteractor: AuthViewInteractorProtocol {
    
    let networker = Networker()
    let networkManager = NetworkManager()
    
    func registerPlayer(player: Player) -> AnyPublisher<Void, Error> {
        let endpoint = "/auth/user/sign-up"
        let playerData = try? JSONEncoder().encode(player)
        
        guard let request = networkManager.createRequestPost(endpoint: endpoint, body: playerData) else {
            fatalError("No se pudo crear la solicitud")
        }
        
        return networker.callServerWithOutReturn(request: request)
    }
    
    func registerClub(club: Club) -> AnyPublisher<Void, Error> {
        let endpoint = "/auth/club/sign-up"
        
        let clubJSON = try? JSONEncoder().encode(club)
        
        guard let request = networkManager.createRequestPost(endpoint: endpoint, body: clubJSON) else {
            fatalError("No se pudo crear la solicitud")
        }
        
        return networker.callServerWithOutReturn(request: request)
    }
    
    func login(email: String, password: String) -> AnyPublisher<String, Error> {
        let endpoint = "/auth/log-in?email=\(email)&password=\(password)"
        
        return networker.callServerLogin(type: String.self, request: networkManager.getSession(endpoint: endpoint, email: email, password: password))
    }
    
    func checkToken() -> AnyPublisher<Token, any Error> {
        let endpoint = "/auth/checkToken"
        
        return networker.callServer(type: Token.self, request: networkManager.getSessionCheckToken(endpoint: endpoint))
    }
}
