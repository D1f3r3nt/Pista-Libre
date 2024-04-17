//
//  AuthViewModel.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 11/4/24.
//

import Foundation
import Combine
import KeychainSwift

@Observable
final class AuthViewModel {
    var goLogin: Bool = false
    var screenStatus: ScreenStatus = .none
    var showAlert: Bool = false
    var showError: Bool = false
    var user: Token?
    var isLogged: Bool = false
    
    @ObservationIgnored var suscriptors = Set<AnyCancellable>()
    @ObservationIgnored var interactor: AuthViewInteractorProtocol
    
    init(
        interactor: AuthViewInteractorProtocol = AuthViewInteractor()) {
            self.interactor = interactor
            checkToken()
        }
    
    func checkEmail(email: String) -> Bool {
        let emailPattern = "^[A-Z0-9a-z._%+-]+@[A-Z0-9a-z.-]+\\.[A-Za-z]{2,64}$"
        let emailPredicate = NSPredicate(format:"SELF MATCHES %@", emailPattern)
        return emailPredicate.evaluate(with: email)
    }
    
    func registerPlayer(username: String, fullname: String, email: String, password: String) {
        let player = Player(username: username, fullname: fullname, email: email, password: password)
        
        interactor.registerPlayer(player: player)
            .sink { completion in
                switch completion {
                case .finished:
                    self.login(email: email, password: password)
                case .failure:
                    self.showAlert = true
                }
            } receiveValue: { _ in
                
            }
            .store(in: &suscriptors)
    }
    
    func registerClub(name: String, location: String, email: String, password: String) {
        let club = Club(name: name, location: location, email: email, password: password)
        
        interactor.registerClub(club: club)
            .sink { completion in
                switch completion {
                case .finished:
                    self.login(email: email, password: password)
                case .failure:
                    self.showAlert = true
                }
            } receiveValue: { _ in
                
            }
            .store(in: &suscriptors)
    }
    
    func login(email: String, password: String) {
        
        interactor.login(email: email, password: password)
            .sink { completion in
                switch completion {
                case .finished:
                    self.isLogged = true
                case .failure(let error):
                    self.showError = true
                    print("ERROR LOGIN \(error.localizedDescription)")
                }
            } receiveValue: { token in
                KeychainManager.shared.token = token
                self.checkToken()
            }
            .store(in: &suscriptors)
    }
    
    func logOut() {
        KeychainManager.shared.token = ""
        self.screenStatus = .none
    }
    
    func checkToken() {
        
        interactor.checkToken()
            .sink { completion in
                switch completion {
                case .finished:
                    break
                case .failure:
                    break
                }
            } receiveValue: { response in
                self.user = response
                
                switch response.type {
                case "user":
                    self.screenStatus = .goHomePlayer
                    
                case "club":
                    self.screenStatus = .goHomeClub
                    
                default:
                    self.screenStatus = .none
                }
            }
            .store(in: &suscriptors)
        
    }
}
