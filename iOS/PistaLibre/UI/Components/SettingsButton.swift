//
//  SettingsButton.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 15/4/24.
//

import SwiftUI

struct SettingsButton: View {
    var body: some View {
        Circle()
            .frame(width: 28, height: 28)
            .opacity(0.6)
            .overlay {
                Image(systemName: "gearshape.fill")
                    .foregroundStyle(Color.whitePL)
            }
            
    }
}

#Preview {
    SettingsButton()
}
