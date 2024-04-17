//
//  Networker.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 11/4/24.
//

import Foundation
import Combine

protocol NetworkerProtocol: AnyObject {
    func callServer<T>(type: T.Type, request: URLRequest?) -> AnyPublisher<T, Error> where T: Decodable
    func callServerWithOutReturn(request: URLRequest?) -> AnyPublisher<Void, Error>
    func callServerLogin<T>(type: T.Type, request: URLRequest?) -> AnyPublisher<T, Error> where T : Decodable
}

final class Networker: NetworkerProtocol {
    func callServerLogin<T>(type: T.Type, request: URLRequest?) -> AnyPublisher<T, Error> where T : Decodable {
        
        guard let request = request else {
            return Fail(error: URLError(.badURL)).eraseToAnyPublisher()
        }
        
        return URLSession.shared.dataTaskPublisher(for: request)
            .tryMap {
                guard let response = $0.response as? HTTPURLResponse, response.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
                return String(data: $0.data, encoding: .utf8) as! T
            }
            .receive(on: DispatchQueue.main)
            .eraseToAnyPublisher()
    }
    
    func callServerWithOutReturn(request: URLRequest?) -> AnyPublisher<Void, Error> {
        
        guard let request = request else {
            return Fail(error: URLError(.badURL)).eraseToAnyPublisher()
        }
        
        return URLSession.shared.dataTaskPublisher(for: request)
            .tryMap {
                guard let response = $0.response as? HTTPURLResponse, (response.statusCode == 201 || response.statusCode == 200) else {
                    throw URLError(.badServerResponse)
                }
            }
            .receive(on: DispatchQueue.main)
            .eraseToAnyPublisher()
    }
    
    func callServer<T>(type: T.Type, request: URLRequest?) -> AnyPublisher<T, Error> where T : Decodable {
        
        guard let request = request else {
            return Fail(error: URLError(.badURL)).eraseToAnyPublisher()
        }
        
        return URLSession.shared.dataTaskPublisher(for: request)
            .tryMap {
                guard let response = $0.response as? HTTPURLResponse, response.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
                return $0.data
            }
            .decode(type: T.self, decoder: JSONDecoder())
            .receive(on: DispatchQueue.main)
            .eraseToAnyPublisher()
    }
}
