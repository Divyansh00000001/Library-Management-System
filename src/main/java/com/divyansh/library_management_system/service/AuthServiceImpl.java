package com.divyansh.library_management_system.service;
import com.divyansh.library_management_system.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.divyansh.library_management_system.util.JwtUtil;

import com.divyansh.library_management_system.dto.RegisterRequest;
import com.divyansh.library_management_system.entity.Role;
import com.divyansh.library_management_system.entity.User;
import com.divyansh.library_management_system.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        return "User registered successfully";
    }
    
    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return "User not found";
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return "Invalid password";
        }

        return JwtUtil.generateToken(user.getEmail());
    }
}