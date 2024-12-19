package com.melo.employee_reimbursement_system.dto;

public class ReimbursementDTO {

    private long reimbId;
    private String description;
    private double amount;
    private String status;
    private UserDTO userDTO;

    // Constructor
    public ReimbursementDTO(long reimbId, String description, double amount, String status, UserDTO userDTO) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userDTO = userDTO;
    }

    // Getters and Setters
    public long getReimbId() {
        return reimbId;
    }

    public void setReimbId(long reimbId) {
        this.reimbId = reimbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
