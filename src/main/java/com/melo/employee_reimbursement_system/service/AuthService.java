package com.melo.employee_reimbursement_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.melo.employee_reimbursement_system.Repository.RoleRepository;
import com.melo.employee_reimbursement_system.Repository.UsersRepository;
import com.melo.employee_reimbursement_system.dto.LoginRequest;
import com.melo.employee_reimbursement_system.model.AuthenticationResponse;
import com.melo.employee_reimbursement_system.model.Role;
import com.melo.employee_reimbursement_system.model.Users;

@Service
public class AuthService {
    
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(Users request) {
        Users user = new Users();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if(user.getRole() == null) {
            Role defaultRole = roleRepository.findById(2L).orElseThrow();
            user.setRole(defaultRole);
        }

        user = usersRepository.save(user);

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            )
        );

        Users user = new Users();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(loginRequest.getPassword());

        user = usersRepository.findByUsername(user.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}
