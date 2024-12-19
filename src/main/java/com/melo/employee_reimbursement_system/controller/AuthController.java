package com.melo.employee_reimbursement_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melo.employee_reimbursement_system.dto.LoginRequest;
import com.melo.employee_reimbursement_system.model.AuthenticationResponse;
import com.melo.employee_reimbursement_system.model.Users;
import com.melo.employee_reimbursement_system.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Users request
            ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest loginRequest
            ) {
        
        Users user = new Users();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(loginRequest.getPassword());
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }
}
