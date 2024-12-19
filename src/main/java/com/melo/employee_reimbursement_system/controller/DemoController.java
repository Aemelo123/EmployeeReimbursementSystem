package com.melo.employee_reimbursement_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url.");
    }

    @GetMapping("manager-only")
    public ResponseEntity<String> managerOnly(){
        return ResponseEntity.ok("Hello from secured Manager url.");
    }
}
