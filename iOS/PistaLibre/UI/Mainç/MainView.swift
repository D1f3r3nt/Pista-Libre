//
//  MainView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 16/4/24.
//

import SwiftUI

struct MainView: View {
    @Binding var viewModel: AuthViewModel
    var body: some View {
        switch viewModel.screenStatus {
        case .goHomePlayer:
            HomePlayerView(viewModel: $viewModel)
        case .goHomeClub:
            HomeClubView(viewModel: $viewModel)
        case .none:
            LoginView(viewModel: $viewModel)
        }
    }
}

#Preview {
    MainView(viewModel: .constant(AuthViewModel()))
}
