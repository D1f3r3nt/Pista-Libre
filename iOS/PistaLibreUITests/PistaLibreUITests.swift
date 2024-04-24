//
//  PistaLibreUITests.swift
//  PistaLibreUITests
//
//  Created by Pablo Marín Gallardo on 9/4/24.
//

import XCTest
import SwiftUI
import ViewInspector
@testable import PistaLibre

final class PistaLibreUITests: XCTestCase {
    
    func testSplashView() throws {
        let splashView = SplashView(isActive: .constant(true))
        
        let pistaText = try splashView.inspect().find(viewWithId: 0)
        XCTAssertNotNil(pistaText)
        
        let librText = try splashView.inspect().find(viewWithId: 1)
        XCTAssertNotNil(librText)
        
        let eText = try splashView.inspect().find(viewWithId: 2)
        XCTAssertNotNil(eText)
    }
    
    func testLoginView() throws {
        
        let viewModel = AuthViewModel()
        
        let viewModelBinding = Binding.constant(viewModel)
        
        let loginView = LoginView(viewModel: viewModelBinding)
        XCTAssertNotNil(loginView)
        
        let logTitle = try loginView.inspect().find(viewWithId: 1)
        XCTAssertNotNil(logTitle)
        
        let inTitle = try loginView.inspect().find(viewWithId: 2)
        XCTAssertNotNil(inTitle)
        
        let emailTextField = try loginView.inspect().find(viewWithId: 3)
        XCTAssertNotNil(emailTextField)
        
        let passwordSecureField = try loginView.inspect().find(viewWithId: 4)
        XCTAssertNotNil(passwordSecureField)
        
        let button = try loginView.inspect().find(viewWithId: 5)
        XCTAssertNotNil(button)
        
        let question = try loginView.inspect().find(viewWithId: 6)
        XCTAssertNotNil(question)
        
        let register = try loginView.inspect().find(viewWithId: 7)
        XCTAssertNotNil(register)
    }
    
    func testChooseRegistrationView() throws {
        
        let viewModel = AuthViewModel()
        
        let chooseView = ChooseRegistrationView(viewModel: .constant(viewModel))
        XCTAssertNotNil(chooseView)
        
        let registrTitle = try chooseView.inspect().find(viewWithId: 0)
        XCTAssertNotNil(registrTitle)
        
        let oTitle = try chooseView.inspect().find(viewWithId: 1)
        XCTAssertNotNil(oTitle)
        
        let navigationPlayer = try chooseView.inspect().find(viewWithId: 2)
        XCTAssertNotNil(navigationPlayer)
        
        let navigationClub = try chooseView.inspect().find(viewWithId: 3)
        XCTAssertNotNil(navigationClub)
        
        let questionText = try chooseView.inspect().find(viewWithId: 4)
        XCTAssertNotNil(questionText)
        
        let buttonGoLogin = try chooseView.inspect().find(viewWithId: 5)
        XCTAssertNotNil(buttonGoLogin)
    }
    
    func testPlayerSignUpView() throws {
        
        let viewModel = AuthViewModel()
        
        let playerSignUpView = PlayerSignUpView(viewModel: .constant(viewModel))
        XCTAssertNotNil(playerSignUpView)
        
        let registroTitle = try playerSignUpView.inspect().find(viewWithId: 0)
        XCTAssertNotNil(registroTitle)
        
        let jugadorTitle = try playerSignUpView.inspect().find(viewWithId: 1)
        XCTAssertNotNil(jugadorTitle)
        
        let fullName = try playerSignUpView.inspect().find(viewWithId: 2)
        XCTAssertNotNil(fullName)
        
        let username = try playerSignUpView.inspect().find(viewWithId: 3)
        XCTAssertNotNil(username)
        
        let email = try playerSignUpView.inspect().find(viewWithId: 4)
        XCTAssertNotNil(email)
        
        let password = try playerSignUpView.inspect().find(viewWithId: 5)
        XCTAssertNotNil(password)
        
        let repeatPassword = try playerSignUpView.inspect().find(viewWithId: 6)
        XCTAssertNotNil(repeatPassword)
        
        let navigationToClub = try playerSignUpView.inspect().find(viewWithId: 7)
        XCTAssertNotNil(navigationToClub)
        
        let questionText = try playerSignUpView.inspect().find(viewWithId: 8)
        XCTAssertNotNil(questionText)
        
        let buttonGoLogin = try playerSignUpView.inspect().find(viewWithId: 9)
        XCTAssertNotNil(buttonGoLogin)
    }
    
    func testClubSignUpView() throws {
        let viewModel = AuthViewModel()
        
        let clubSignUpView = ClubSignUpView(viewModel: .constant(viewModel))
        XCTAssertNotNil(clubSignUpView)
        
        let registroTitle = try clubSignUpView.inspect().find(viewWithId: 0)
        XCTAssertNotNil(registroTitle)
        
        let clubTitle = try clubSignUpView.inspect().find(viewWithId: 1)
        XCTAssertNotNil(clubTitle)
        
        let name = try clubSignUpView.inspect().find(viewWithId: 2)
        XCTAssertNotNil(name)
        
        let location = try clubSignUpView.inspect().find(viewWithId: 3)
        XCTAssertNotNil(location)
        
        let email = try clubSignUpView.inspect().find(viewWithId: 4)
        XCTAssertNotNil(email)
        
        let password = try clubSignUpView.inspect().find(viewWithId: 5)
        XCTAssertNotNil(password)
        
        let repeatPassword = try clubSignUpView.inspect().find(viewWithId: 6)
        XCTAssertNotNil(repeatPassword)
        
        let navigationToPlayer = try clubSignUpView.inspect().find(viewWithId: 7)
        XCTAssertNotNil(navigationToPlayer)
        
        let questionText = try clubSignUpView.inspect().find(viewWithId: 8)
        XCTAssertNotNil(questionText)
        
        let buttonGoLogin = try clubSignUpView.inspect().find(viewWithId: 9)
        XCTAssertNotNil(buttonGoLogin)
    }
}
