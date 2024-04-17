//
//  CustomTextFieldView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 11/4/24.
//

import SwiftUI

struct CustomTextFieldView: View {
    
    @State private var isTextVisible: Bool = false
    @Binding var textField: String
    @FocusState var focusPassword: Bool
    
    var placeholder: String
    var isPasswordField: Bool
    
    var body: some View {
        ZStack(alignment: .leading) {
            if textField.isEmpty {
                Text(placeholder)
                    .font(.system(size: 14))
                    .foregroundStyle(Color.lightGrayPL)
                    .padding(EdgeInsets(top: 9, leading: 12, bottom: 9, trailing: 0))
            }
            
            if isPasswordField && !isTextVisible {
                SecureField("", text: $textField)
                    .textInputAutocapitalization(.never)
                    .padding(.leading, 12)
                    .focused($focusPassword)
                
            } else {
                TextField("", text: $textField)
                    .textInputAutocapitalization(.never)
                    .padding(.leading, 12)
            }
            
            HStack {
                Spacer()
                if isPasswordField {
                    Button(action: {
                        isTextVisible.toggle() // Cambia entre mostrar y ocultar la contraseña
                    }) {
                        Image(isTextVisible ? "close.eye" : "eye")
                            .foregroundColor(.gray)
                    }
                    .padding(.trailing, 12)
                }
            }
        }
        .frame(height: 34)
        .background(Color.whitePL)
        .clipShape(RoundedRectangle(cornerRadius: 8))
        
        
    }
}

#Preview {
    ZStack {
        Color.blackGradientPL
        
        CustomTextFieldView(textField: .constant(""), placeholder: "Correo electrónico", isPasswordField: true)
    }
}
