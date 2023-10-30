package com.nib.runningapp.services;

import com.nib.runningapp.dtos.GoogleUserDTO;
import com.nib.runningapp.dtos.UserDTO;

import java.util.Map;

public interface AuthService {
    // login
    UserDTO authenticate(GoogleUserDTO googleUserDTO);

    // register
    Map<String, String> register(String username, String password);
}
