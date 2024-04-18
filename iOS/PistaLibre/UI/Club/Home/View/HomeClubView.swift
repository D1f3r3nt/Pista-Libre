//
//  HomeClubView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 16/4/24.
//

import SwiftUI
import KeychainSwift

struct HomeClubView: View {
    @Binding var viewModel: AuthViewModel
    var body: some View {
        ZStack {
            VStack {
                Text("Home Club View")
                
                Button {
                    viewModel.logOut()
                } label: {
                    Text("Cerrar sesión")
                }
            }
        }
        .navigationBarBackButtonHidden(true)
    }
}

#Preview {
    HomeClubView(viewModel: .constant(AuthViewModel()))
}
