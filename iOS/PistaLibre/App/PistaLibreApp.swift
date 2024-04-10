//
//  PistaLibreApp.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 9/4/24.
//

import SwiftUI

@main
struct PistaLibreApp: App {
    
    @State private var isActive = true
    let persistenceController = PersistenceController.shared
    
    var body: some Scene {
        WindowGroup {
            switch isActive {
            case false:
                LoginView()
                    .environment(\.managedObjectContext, persistenceController.container.viewContext)
            case true:
                SplashView(isActive: $isActive)
            }
        }
    }
}
