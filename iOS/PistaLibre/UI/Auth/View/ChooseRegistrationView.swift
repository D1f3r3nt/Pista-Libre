//
//  ChooseRegistrationView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 11/4/24.
//

import SwiftUI

struct ChooseRegistrationView: View {
    @Binding var viewModel: AuthViewModel
    var body: some View {
        NavigationStack {
            ZStack {
                Color.blackGradientPL
                    .ignoresSafeArea()
                
                                
                VStack {
                    HStack(spacing: 0) {
                        Text("Registr")
                            .foregroundStyle(Color.greenPL)
                        
                        Text("o")
                            .foregroundStyle(Color.whitePL)
                    }
                    .font(.customFont(name: .angkor, size: 48))
                    .padding(.top, 90)
                    
                    Spacer()
                    
                    VStack(spacing: 25) {
                        NavigationLink(destination: PlayerSignUpView(viewModel: $viewModel)) {
                            CustomButtonView(
                                nameAction: "Jugador",
                                widht: 145,
                                colorButton: Color.greenPL)
                        }
                        
                        NavigationLink(destination: ClubSignUpView(viewModel: $viewModel)) {
                            CustomButtonView(
                                nameAction: "Club",
                                widht: 145,
                                colorButton: Color.greenPL)
                        }
                    }
                    .padding(.bottom, 40)

                    Spacer()
                    
                    HStack {
                        Text("¿Ya tienes una cuenta?")
                            .foregroundStyle(Color.whitePL)
                        
                        Button {
                            viewModel.goLogin = true
                        } label: {
                            Text("Iniciar sesión")
                                .foregroundStyle(Color.greenPL)
                        }
                    }
                    .padding(.bottom)
                    .navigationDestination(isPresented: $viewModel.goLogin) {
                        LoginView(viewModel: $viewModel)
                    }
                }
            }
            .navigationBarBackButtonHidden(true)
        }
    }
}

#Preview {
    ChooseRegistrationView(viewModel: .constant(AuthViewModel()))
}
