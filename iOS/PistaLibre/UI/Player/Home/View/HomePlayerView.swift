//
//  HomePlayerView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 14/4/24.
//

import SwiftUI

struct HomePlayerView: View {
    @Binding var viewModel: AuthViewModel
    @State var showAlert: Bool = false
    var body: some View {
        NavigationStack {
            ZStack {
                ZStack {
                    Color.greenPL
                        .ignoresSafeArea()
                    VStack {
                        HStack {
                            Text("¡Hola, Pablo!")
                                .font(.system(size: 38))
                                .bold()
                            
                            Spacer()
                            
                            Button {
                                showAlert = true
                            } label: {
                                Image(systemName: "power")
                            }
                            .navigationDestination(isPresented: $viewModel.goLogin) {
                                LoginView(viewModel: $viewModel)
                            }
                            
                            NavigationLink {
                                // TODO: Navegar a la pantalla de settings
                            } label: {
                                SettingsButton()
                            }
                            .foregroundStyle(Color.blackPL)
                        }
                        .padding(.horizontal, 20)
                        
                        Spacer()
                    }
                    .padding(.top, 30)
                }
                
            }
            .navigationBarBackButtonHidden(true)
            .alert("Cerrar sesión", isPresented: $showAlert) {
                Button(action: {
                    viewModel.logOut()
                }, label: {
                    Text("Sí")
                })
                
                Button(action: {
                    showAlert = false
                }, label: {
                    Text("No")
                })
            } message: {
                Text("¿Estás seguro de que deseas cerrar sesión?")
            }

        }
    }
}

#Preview {
    HomePlayerView(viewModel: .constant(AuthViewModel()))
}
