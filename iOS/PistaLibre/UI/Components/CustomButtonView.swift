//
//  CustomButtonView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 11/4/24.
//

import SwiftUI

struct CustomButtonView: View {
    var nameAction: String
    var widht: CGFloat
    var colorButton: Color
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 8)
                .frame(width: widht, height: 34)
                .foregroundStyle(colorButton)
            
            Text(nameAction)
                .font(.system(size: 14))
                .bold()
                .foregroundStyle(Color.whitePL)
        }
    }
}

#Preview {
    CustomButtonView(nameAction: "Entrar", widht: 100, colorButton: Color.greenPL)
}
