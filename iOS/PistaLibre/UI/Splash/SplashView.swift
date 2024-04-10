//
//  SplashView.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 10/4/24.
//

import SwiftUI

struct SplashView: View {
    @Binding var isActive: Bool
    var body: some View {
        ZStack {
            Color.blackGradientPL
                .ignoresSafeArea()
            
            VStack(spacing: 0) {
                Text("PISTA")
                    .padding(.bottom, -46)
                
                HStack(spacing: 0) {
                    Text("LIBR")
                        .foregroundStyle(Color.greenPL)
                    Text("E")
                }
            }
            .font(.customFont(name: .angkor, size: 56))
            .foregroundStyle(Color.whitePL)
        }
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.8) {
                withAnimation(.easeIn(duration: 0.3)) {
                    isActive.toggle()
                }
            }
        }
    }
}

#Preview {
    SplashView(isActive: .constant(false))
}
