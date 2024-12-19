package com.melo.employee_reimbursement_system.model;

public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
