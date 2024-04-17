//
//  PlayerSignUpView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 11/4/24.
//

import SwiftUI

struct PlayerSignUpView: View {
    @Binding var viewModel: AuthViewModel
    @State var fullName: String = ""
    @State var username: String = ""
    @State var email: String = ""
    @State var password: String = ""
    @State var passwordRepeat: String = ""
    
    // Verifica que las contraseñas coincidan
    var passwordsMatch: Bool {
        !password.isEmpty && password == passwordRepeat
    }
    
    // Verifica que ningún campo esté vacío
    var fieldsAreValid: Bool {
        !fullName.isEmpty && !username.isEmpty && !email.isEmpty && !password.isEmpty && !passwordRepeat.isEmpty
    }
    
    // Verifica la validez del formato del correo electrónico
    var emailIsValid: Bool {
        let emailPattern = "^[A-Z0-9a-z._%+-]+@[A-Z0-9a-z.-]+\\.[A-Za-z]{2,64}$"
        let emailPredicate = NSPredicate(format:"SELF MATCHES %@", emailPattern)
        return emailPredicate.evaluate(with: email)
    }
    
    var body: some View {
        NavigationStack {
            ZStack {
                Color.blackGradientPL
                    .ignoresSafeArea()
                
                //                REGISTRO JUGADOR
                VStack {
                    VStack(spacing: -40){
                        Text("Registro")
                            .foregroundStyle(Color.whitePL)
                        Text("jugador")
                            .foregroundStyle(Color.greenPL)
                    }
                    .font(.customFont(name: .angkor, size: 48))
                    .padding(.top)
                    
                    Spacer()
                    
                    //                    FORMULARIO
                    VStack(spacing: 32) {
                        CustomTextFieldView(
                            textField: $fullName,
                            placeholder: "Nombre completo",
                            isPasswordField: false)
                        
                        CustomTextFieldView(
                            textField: $username,
                            placeholder: "Nombre de usuario",
                            isPasswordField: false)
                        
                        CustomTextFieldView(
                            textField: $email,
                            placeholder: "Correo electrónico",
                            isPasswordField: false)
                        
                        CustomTextFieldView(
                            textField: $password,
                            placeholder: "Contraseña",
                            isPasswordField: true)
                        
                        CustomTextFieldView(
                            textField: $passwordRepeat,
                            placeholder: "Repetir contraseña",
                            isPasswordField: true)
                    }
                    .padding(.horizontal, 48)
                    
                    Spacer()
                    
                    //                    BOTÓN
                    
                    if passwordsMatch && fieldsAreValid && emailIsValid {
                        Button(action: {
                            viewModel.registerPlayer(
                                username: username,
                                fullname: fullName,
                                email: email,
                                password: password)
                        }, label: {
                            CustomButtonView(nameAction: "Regístrate", widht: 100, colorButton: Color.greenPL)
                        })
                    } else {
                        CustomButtonView(
                            nameAction: "Regístrate",
                            widht: 100,
                            colorButton: Color.lightGrayPL)
                    }
                    
                    
                    Spacer()
                    
                    //                    NAVEGACIÓN
                    VStack(spacing: 28) {
                        NavigationLink(destination: ClubSignUpView(viewModel: $viewModel)) {
                            Text("Registrarme como club")
                        }
                        
                        HStack {
                            Text("¿Ya tienes una cuenta?")
                                .foregroundStyle(Color.whitePL)
                            
                            Button {
                                viewModel.goLogin = true
                            } label: {
                                Text("Iniciar sesión")
                            }
                        }
                        .navigationDestination(isPresented: $viewModel.goLogin) {
                            LoginView(viewModel: $viewModel)
                        }
                    }
                    .foregroundStyle(Color.greenPL)
                }
                .navigationBarBackButtonHidden(true)
                .alert(isPresented: $viewModel.showAlert) {
                    Alert(
                        title: Text("Error de registro"),
                        message: Text("Este usuario ya existe.")
                    )
                }
            }
        }
    }
}

#Preview {
    PlayerSignUpView(viewModel: .constant(AuthViewModel()))
}
