package com.melo.employee_reimbursement_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melo.employee_reimbursement_system.Repository.UsersRepository;
import com.melo.employee_reimbursement_system.model.Users;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users getUserById(long id){
        return usersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public Optional<Users> deleteUserById(long id){
        Optional<Users> user = usersRepository.findById(id);
        usersRepository.deleteById(id);
        return user;
    }

    public boolean existsByUsername(String username){
        return usersRepository.existsByUsername(username);
    }

    public Optional<Users> findByUsername(String username){
        return usersRepository.findByUsername(username);
    }

}
