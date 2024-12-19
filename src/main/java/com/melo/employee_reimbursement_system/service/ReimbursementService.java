package com.melo.employee_reimbursement_system.service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.melo.employee_reimbursement_system.Repository.ReimbursementRepository;
import com.melo.employee_reimbursement_system.Repository.UsersRepository;
import com.melo.employee_reimbursement_system.dto.ReimbursementDTO;
import com.melo.employee_reimbursement_system.dto.UserDTO;
import com.melo.employee_reimbursement_system.model.Reimbursement;
import com.melo.employee_reimbursement_system.model.Users;

@Service
public class ReimbursementService {


    @Autowired
    private ReimbursementRepository reimbursementRepository;

    @Autowired
    private UsersRepository usersRepository;

        // Helper method to create UserDTO from Users object
        private UserDTO createUserDTO(Users user) {
            return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole(),
                user.getFirstname(),
                user.getLastname()
            );
        }
    
        // Helper method to map Reimbursement to ReimbursementDTO
        private ReimbursementDTO createReimbursementDTO(Reimbursement reimbursement, UserDTO userDTO) {
            return new ReimbursementDTO(
                reimbursement.getReimbId(),
                reimbursement.getDescription(),
                reimbursement.getAmount(),
                reimbursement.getStatus(),
                userDTO
            );
        }

    public ReimbursementDTO createReimbursement(ReimbursementDTO reimbursementDTO){
        // Getting the authenticated user's username from SecurityContext
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Users user = usersRepository.findByUsername(username).orElseThrow();

        UserDTO userDTO = createUserDTO(user);

        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setDescription(reimbursementDTO.getDescription());
        reimbursement.setAmount(reimbursementDTO.getAmount());
        reimbursement.setUser(user);
        reimbursement.setStatus("PENDING");

        reimbursement = reimbursementRepository.save(reimbursement);

        return createReimbursementDTO(reimbursement, userDTO);
    }

    public List<ReimbursementDTO> getAllReimbursementsByUsername(String username){
        List<Reimbursement> reimbursements = reimbursementRepository.findByUser_Username(username);

        return reimbursements.stream()
            .map(reimbursement -> {
                UserDTO userDTO = createUserDTO(reimbursement.getUser());

                return createReimbursementDTO(reimbursement, userDTO);
        })
        .collect(Collectors.toList());
    }

    public ReimbursementDTO getReimbursementByReimbursementId(long reimbId){
        Reimbursement reimbursement = reimbursementRepository.findById(reimbId).orElseThrow();

        UserDTO userDTO = createUserDTO(reimbursement.getUser());

        return createReimbursementDTO(reimbursement, userDTO);
    }

    public Reimbursement deleteReimbursementById(long reimbId) {
        Reimbursement reimbursement = reimbursementRepository.findById(reimbId)
            .orElseThrow(() -> new RuntimeException("Reimbursement not found with id: " + reimbId));
    
        reimbursementRepository.deleteById(reimbId);
  
        return reimbursement;
    }

    public ReimbursementDTO updateDescriptionById(long reimbId, String newDescription) throws AccessDeniedException {

        Reimbursement reimbursement = reimbursementRepository.findById(reimbId).orElseThrow();

        //check if that is their ticket if not don't allow them to update it
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if (!reimbursement.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You can only update your own reimbursements.");
        }
        
        reimbursement.setDescription(newDescription);
        reimbursement = reimbursementRepository.save(reimbursement);

        UserDTO userDTO = createUserDTO(reimbursement.getUser());

        return createReimbursementDTO(reimbursement, userDTO);

    }
}
