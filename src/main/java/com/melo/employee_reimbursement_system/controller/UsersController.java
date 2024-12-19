package com.melo.employee_reimbursement_system.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melo.employee_reimbursement_system.dto.ReimbursementDTO;
import com.melo.employee_reimbursement_system.model.Users;
import com.melo.employee_reimbursement_system.service.ReimbursementService;
import com.melo.employee_reimbursement_system.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ReimbursementService reimbursementService;

    @GetMapping("/me")
    public Users getCurrentUser() {
        // Getting the authenticated user's username from SecurityContext
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        return usersService.findByUsername(username).orElseThrow();
    }

    @PostMapping("/me/reimbursement")
    public ReimbursementDTO createReimbursement(@RequestBody ReimbursementDTO reimbursementDTO) {
        return reimbursementService.createReimbursement(reimbursementDTO);
    }

    @GetMapping("/me/all")
    public List<ReimbursementDTO> getAllMyReimbursements() {
        // Getting the authenticated user's username from SecurityContext
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        return reimbursementService.getAllReimbursementsByUsername(username);
    }

    @PatchMapping("/me/{reimbId}")
    public ReimbursementDTO updateDescriptionofReimbursement(@PathVariable long reimbId, @RequestBody Map<String, Object> requestBody) throws AccessDeniedException {
        String newDescription = (String) requestBody.get("newDescription");
        return reimbursementService.updateDescriptionById(reimbId, newDescription);
    }

}
