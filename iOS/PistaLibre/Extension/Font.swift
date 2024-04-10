//
//  Font.swift
//  PistaLibre
//
//  Created by Pablo Marín Gallardo on 9/4/24.
//

import SwiftUI

extension Font {
    
    enum CustomFont: String {
        case angkor = "Angkor-Regular"
        case roboto = "Roboto-Regular"
    }
    
    static func customFont(name: CustomFont, size: CGFloat) -> Font {
        return Font.custom(name.rawValue, size: size)
    }
}
