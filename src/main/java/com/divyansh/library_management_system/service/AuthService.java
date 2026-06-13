package com.divyansh.library_management_system.service;

import com.divyansh.library_management_system.dto.LoginRequest;
import com.divyansh.library_management_system.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}