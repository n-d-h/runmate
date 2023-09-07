package com.nib.runningapp.services;

import java.util.Map;

public interface AuthService {
    // login
    String authenticate(String username, String password);

    // register
    Map<String, String> register(String username, String password);
}
