package com.nib.runningapp.services;

import com.nib.runningapp.dtos.GoogleUserDTO;

import java.util.Map;

public interface AuthService {
    // login
    String authenticate(GoogleUserDTO googleUserDTO);

    // register
    Map<String, String> register(String username, String password);
}
