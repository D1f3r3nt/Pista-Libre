//
//  Color.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 9/4/24.
//

import SwiftUI

extension Color {
    
    init(hex: String) {
            let hex = hex.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
            var int: UInt64 = 0
            Scanner(string: hex).scanHexInt64(&int)
            let a, r, g, b: UInt64
            switch hex.count {
            case 3:
                (a, r, g, b) = (255, (int >> 8) * 17, (int >> 4 & 0xF) * 17, (int & 0xF) * 17)
            case 6:
                (a, r, g, b) = (255, int >> 16, int >> 8 & 0xFF, int & 0xFF)
            case 8:
                (a, r, g, b) = (int >> 24, int >> 16 & 0xFF, int >> 8 & 0xFF, int & 0xFF)
            default:
                (a, r, g, b) = (255, 0, 0, 0)
            }
            self.init(.sRGB, red: Double(r) / 255, green: Double(g) / 255, blue:  Double(b) / 255, opacity: Double(a) / 255)
        }
    
    static let blackGradientPL = LinearGradient(
        gradient: Gradient(colors: [Color(hex: "0C0C0C"), Color(hex: "2E2E2E")]),
        startPoint: .topLeading,
        endPoint: .bottomTrailing
    )
    
    static let greenPL = Color(hex: "92D024")
    static let whitePL = Color(hex: "FFFFFF")
    static let redPL = Color(hex: "FF0000")
    static let blackPL = Color(hex: "000000")
    static let lightGrayPL = Color(hex: "A4A4A4")
    static let orangePL = Color(hex: "DA6B1A")
    static let bluePL = Color (hex: "007AFF")
    
}
