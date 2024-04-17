//
//  PistaLibreApp.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 9/4/24.
//

import SwiftUI
import KeychainSwift

@main
struct PistaLibreApp: App {
    @State var viewModel = AuthViewModel()
    @State private var isActive = true
    let persistenceController = PersistenceController.shared
    
    
    var body: some Scene {
        WindowGroup {
            switch isActive {
            case false:
                
                if !KeychainManager.shared.token.isEmpty {
                    MainView(viewModel: $viewModel)
                } else {
                    LoginView(viewModel: $viewModel)
                        .environment(\.managedObjectContext, persistenceController.container.viewContext)
                }
                
            case true:
                SplashView(isActive: $isActive)
            }
        }
    }
}
