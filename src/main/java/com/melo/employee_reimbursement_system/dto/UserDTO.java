package com.melo.employee_reimbursement_system.dto;

import com.melo.employee_reimbursement_system.model.Role;

public class UserDTO {

    private long userId;
    private String firstname;
    private String lastname;
    private String username;
    private Role role;

    public UserDTO(Long userId, String username, Role role, String firstname, String lastname) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public long getUserId() {
        return userId;
    }

    public void setReimbdId(long userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role){
        this.role = role;
    }
}
