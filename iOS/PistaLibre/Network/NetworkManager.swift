//
//  NetworkManager.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 11/4/24.
//

import Foundation
import KeychainSwift

enum HttpMethods: String {
    case get = "GET"
    case post = "POST"
}

struct NetworkManager {
    
    func getSession(endpoint: String, email: String, password: String) -> URLRequest? {
        
        var components = URLComponents(string: "\(baseURL)\(endpoint)")
        
        let queryItems = [
            URLQueryItem(name: "email", value: email),
            URLQueryItem(name: "password", value: password)
        ] 
        
        components?.queryItems = queryItems
        
        guard let url = components?.url else {
            print("Error: No se pudo crear la URL")
            return nil
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = HttpMethods.get.rawValue
        
        return request
    }
    
    func createRequestPost(endpoint: String, body: Data? = nil) -> URLRequest? {
        guard let url = URL(string: "\(baseURL)\(endpoint)") else {
            return nil
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = HttpMethods.post.rawValue
        request.httpBody = body
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        return request
    }
    
    func getSessionCheckToken(endpoint: String) -> URLRequest? {
        guard let url = URL(string: "\(baseURL)\(endpoint)") else {
            return nil
        }
        let keychain = KeychainSwift()
        
        
        var request = URLRequest(url: url)
        request.httpMethod = HttpMethods.get.rawValue
        if let token = keychain.get("token") {
            request.addValue("Bearer \(token)", forHTTPHeaderField: "Authorization")
        }
        
        return request
    }
}
