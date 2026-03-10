package com.jobportal.job_portal.service;

import com.jobportal.job_portal.entity.Role;
import com.jobportal.job_portal.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jobportal.job_portal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user){

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(Role.CANDIDATE);
        return userRepository.save(user);
    }

    public  Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
