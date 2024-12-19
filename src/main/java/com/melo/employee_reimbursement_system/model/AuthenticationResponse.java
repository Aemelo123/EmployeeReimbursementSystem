package com.melo.employee_reimbursement_system.model;

import com.melo.employee_reimbursement_system.dto.UserDTO;

public class AuthenticationResponse {

    private String token;
    private UserDTO userDTO;   

    public AuthenticationResponse(String token, UserDTO userDTO){
        this.token = token;
        this.userDTO = userDTO;
    }

    public String getToken() {
        return token;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }
}
