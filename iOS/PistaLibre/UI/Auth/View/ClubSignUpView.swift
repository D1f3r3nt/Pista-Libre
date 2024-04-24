//
//  ClubSignUpView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 11/4/24.
//

import SwiftUI

struct ClubSignUpView: View {
    @Binding var viewModel: AuthViewModel
    @State var clubName: String = ""
    @State var location: String = ""
    @State var email: String = ""
    @State var password: String = ""
    @State var passwordRepeat: String = ""
    
    // Verifica que las contraseñas coincidan
    var passwordsMatch: Bool {
        password == passwordRepeat
    }
    
    // Verifica que ningún campo esté vacío
    var fieldsAreValid: Bool {
        !clubName.isEmpty && !location.isEmpty && !email.isEmpty && !password.isEmpty && !passwordRepeat.isEmpty
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
                
                VStack {
                    //                REGISTRO CLUB
                    VStack(spacing: -30) {
                        Text("Registro")
                            .foregroundStyle(Color.whitePL)
                            .id(0)
                        
                        Text("club")
                            .foregroundStyle(Color.greenPL)
                            .id(1)
                    }
                    .font(.customFont(name: .angkor, size: 48))
                    .padding(.top)
                    
                    Spacer()
                    
                    //                FORMULARIO
                    VStack(spacing: 32) {
                        CustomTextFieldView(
                            textField: $clubName,
                            placeholder: "Nombre del club",
                            isPasswordField: false)
                        .id(2)
                        
                        CustomTextFieldView(
                            textField: $location,
                            placeholder: "Dirección",
                            isPasswordField: false)
                        .id(3)
                        
                        CustomTextFieldView(
                            textField: $email,
                            placeholder: "Correo electrónico",
                            isPasswordField: false)
                        .id(4)
                        
                        CustomTextFieldView(
                            textField: $password,
                            placeholder: "Contraseña",
                            isPasswordField: true)
                        .id(5)
                        
                        CustomTextFieldView(
                            textField: $passwordRepeat,
                            placeholder: "Repetir contraseña",
                            isPasswordField: true)
                        .id(6)
                    }
                    .padding(.horizontal, 48)
                    
                    Spacer()
                    
                    //                BOTÓN
                    if passwordsMatch && fieldsAreValid && emailIsValid {
                        Button(action: {
                            viewModel.registerClub(
                                name: clubName,
                                location: location,
                                email: email,
                                password: password)
                        }, label: {
                            CustomButtonView(
                                nameAction: "Registrar club",
                                widht: 145,
                                colorButton: Color.greenPL)
                        })
                    } else {
                        CustomButtonView(
                            nameAction: "Registrar club",
                            widht: 145,
                            colorButton: Color.lightGrayPL)
                    }
                    
                    Spacer()
                    
                    //                NAVEGACIÓN
                    VStack(spacing: 28) {
                        NavigationLink(destination: PlayerSignUpView(viewModel: $viewModel)) {
                            Text("Registrarme como jugador")
                        }
                        .id(7)
                        
                        HStack {
                            Text("¿Ya tienes una cuenta?")
                                .foregroundStyle(Color.whitePL)
                                .id(8)
                            
                            Button {
                                viewModel.goLogin = true
                            } label: {
                                Text("Iniciar sesión")
                            }
                            .id(9)
                        }
                        .navigationDestination(isPresented: $viewModel.goLogin) {
                            LoginView(viewModel: $viewModel)
                        }
                    }
                    .foregroundStyle(Color.greenPL)
                }
            }
            .navigationBarBackButtonHidden(true)
            .alert(isPresented: $viewModel.showAlert) {
                Alert(
                    title: Text("Error de registro"),
                    message: Text("Este club ya existe.")
                )
            }
        }
    }
}

#Preview {
    ClubSignUpView(viewModel: .constant(AuthViewModel()))
}
