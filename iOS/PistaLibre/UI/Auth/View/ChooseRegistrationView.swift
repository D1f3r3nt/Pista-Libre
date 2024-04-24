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
                            .id(0)
                        
                        Text("o")
                            .foregroundStyle(Color.whitePL)
                            .id(1)
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
                        .id(2)
                        
                        NavigationLink(destination: ClubSignUpView(viewModel: $viewModel)) {
                            CustomButtonView(
                                nameAction: "Club",
                                widht: 145,
                                colorButton: Color.greenPL)
                        }
                        .id(3)
                    }
                    .padding(.bottom, 40)

                    Spacer()
                    
                    HStack {
                        Text("¿Ya tienes una cuenta?")
                            .foregroundStyle(Color.whitePL)
                            .id(4)
                        
                        Button {
                            viewModel.goLogin = true
                        } label: {
                            Text("Iniciar sesión")
                                .foregroundStyle(Color.greenPL)
                        }
                        .id(5)
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
