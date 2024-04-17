//
//  Keychain.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 16/4/24.
//

import Foundation
import KeychainSwift

protocol KeychainManagerProtocol: AnyObject {
    var token: String { get set }}

class KeychainManager: KeychainManagerProtocol {
    
    static let shared = KeychainManager()
    
    let keychain = KeychainSwift()
    let tokenKey = "token"
    
    var token: String {
        get {
            return keychain.get(tokenKey) ?? ""
        }
        
        set {
            keychain.set(newValue, forKey: tokenKey)
        }
    }
}
