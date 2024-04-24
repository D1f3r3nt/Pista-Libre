//
//  NetworkManagerTests.swift
//  PistaLibreTests
//
//  Created by Pablo Marín Gallardo on 22/4/24.
//

import XCTest
@testable import PistaLibre

final class NetworkManagerTests: XCTestCase {
    
    private var sut = NetworkManager()
    
    //    Función getSession
    func testGetSessionWithValidData() {
        let email = "test@example.com"
        let password = "password123"
        let endpoint = "/auth/log-in?email=\(email)&password=\(password)"
        
        let request = sut.getSession(endpoint: endpoint, email: email, password: password)
        
        XCTAssertNotNil(request, "La URLRequest no debería ser nil")
        XCTAssertEqual(request?.httpMethod, "GET", "El método HTTP debe ser GET")
        XCTAssertTrue(request?.url?.absoluteString.contains("email=test@example.com") ?? false, "La URL debe contener el email")
        XCTAssertTrue(request?.url?.absoluteString.contains("password=password123") ?? false, "La URL debe contener la contraseña")
    }
    
    func testGetSessionWithEmptyEndpoint() {
        let request = sut.getSession(endpoint: "", email: "test@example.com", password: "password123")
        
        XCTAssertNotNil(request, "La URLRequest no debería ser nil incluso con endpoint vacío")
    }
    
    func testGetSessionWithNilURL() {
        let request = sut.getSession(endpoint: "%%%", email: "test@example.com", password: "password123")
        
        XCTAssertNil(request, "La URLRequest debería ser nil si la URL es inválida")
    }
    
//    Función createRequestPost
    func testCreateRequestPostWithValidURL() {
        
        let endpoint = "/auth/user/sign-up"
        
        let request = sut.createRequestPost(endpoint: endpoint)
        
        XCTAssertNotNil(request, "La solicitud no debería ser nil")
        XCTAssertEqual(request?.httpMethod, "POST", "El método HTTP debería ser POST")
        XCTAssertEqual(request?.url?.absoluteString, "\(baseURL)\(endpoint)", "La URL de la solicitud no coincide con la esperada")
        XCTAssertEqual(request?.value(forHTTPHeaderField: "Content-Type"), "application/json", "El tipo de contenido debe ser application/json")
    }
    
    func testCreateRequestPostWithInvalidURL() {
        let request = sut.createRequestPost(endpoint: "esto no es una URL")
        XCTAssertNil(request, "La solicitud debería ser nil con una URL inválida")
    }
    
    func testCreateRequestPostWithBody() {
        let endpoint = "/auth/user/sign-up"
        let data = "{\"username\": \"testRegister\", \"fullname\": \"Test Full Name\", \"email\": \"test@example.com\", \"password\": \"passwordTest1234\"}".data(using: .utf8)
        let request = sut.createRequestPost(endpoint: endpoint, body: data)
        XCTAssertNotNil(request, "La solicitud no debería ser nil")
        XCTAssertNotNil(request?.httpBody, "El cuerpo de la solicitud no debería ser nil")
        XCTAssertEqual(String(data: request!.httpBody!, encoding: .utf8), "{\"username\": \"testRegister\", \"fullname\": \"Test Full Name\", \"email\": \"test@example.com\", \"password\": \"passwordTest1234\"}", "El cuerpo de la solicitud no coincide con el esperado")
    }
    
    
}
