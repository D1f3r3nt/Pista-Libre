//
//  LoginView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 9/4/24.
//

import SwiftUI

struct LoginView: View {
    @State var emailTextField: String = ""
    @State var passwordTextField: String = ""
    @Binding var viewModel: AuthViewModel
    @FocusState var focusPassword: Bool
    
    var body: some View {
        NavigationStack{
            ZStack {
                Color.blackGradientPL
                    .ignoresSafeArea()
                
                VStack {
                    HStack(spacing: 14) {
                        Text("Log")
                            .foregroundStyle(Color.whitePL)
                            .id(1)
                        Text("In")
                            .foregroundStyle(Color.greenPL)
                            .id(2)
                    }
                    .font(.customFont(name: .angkor, size: 48))
                    .padding(.top, 100)
                    
                    Spacer()
                    
                    VStack(spacing: 32) {
                        VStack {
                            CustomTextFieldView(
                                textField: $emailTextField,
                                placeholder: "Correo electrónico",
                                isPasswordField: false)
                            .id(3)
                            
                            if focusPassword && !viewModel.checkEmail(email: emailTextField) {
                                HStack {
                                    Text("El formato del correo es incorrecto.")
                                        .font(.system(size: 12))
                                        .foregroundStyle(Color.redPL)
                                    
                                    Spacer()
                                }
                            }
                            
                            if viewModel.showError {
                                
                                HStack {
                                    Text("Los datos introducidos son erróneos.")
                                        .font(.system(size: 12))
                                        .foregroundStyle(Color.redPL)
                                        .onAppear {
                                            DispatchQueue.main.asyncAfter(deadline: .now() + 5) {
                                                viewModel.showError = false
                                            }
                                        }
                                    Spacer()
                                }
                            }
                        }
                        .animation(.snappy(duration: 0.4), value: focusPassword)
                        
                        VStack {
                            CustomTextFieldView(
                                textField: $passwordTextField,
                                focusPassword: _focusPassword,
                                placeholder: "Contraseña",
                                isPasswordField: true)
                            .id(4)
                            
                            
                            HStack {
                                if viewModel.showError {
                                    Text("Los datos introducidos son erróneos.")
                                        .font(.system(size: 12))
                                        .foregroundStyle(Color.redPL)
                                        .onAppear {
                                            DispatchQueue.main.asyncAfter(deadline: .now() + 8) {
                                                viewModel.showError = false
                                            }
                                        }
                                    Spacer()
                                }
                            }
                        }
                        
                        
                        Button(action: {
                            viewModel.login(
                                email: emailTextField,
                                password: passwordTextField)
                        }, label: {
                            CustomButtonView(
                                nameAction: "Entrar",
                                widht: 100,
                                colorButton: Color.greenPL)
                        })
                        .padding(.top, 36)
                        .id(5)
                    }
                    .padding(.horizontal, 48)
                    .animation(.snappy(duration: 0.4), value: viewModel.showError)
                    
                    Spacer()
                    
                    HStack {
                        Text("¿No tienes una cuenta?")
                            .foregroundStyle(Color.whitePL)
                            .id(6)
                        
                        NavigationLink(
                            destination: ChooseRegistrationView(viewModel: $viewModel)) {
                                Text("Crear una cuenta")
                                    .foregroundStyle(Color.greenPL)
                            }
                            .id(7)
                    }
                    .padding(.bottom)
                    .navigationDestination(isPresented: $viewModel.isLogged) {
                        MainView(viewModel: $viewModel)
                    }
                }
            }
            .navigationBarBackButtonHidden(true)
            .onAppear {
                viewModel.goLogin = false
            }
        }
    }
}

#Preview {
    LoginView(viewModel: .constant(AuthViewModel()))
}
