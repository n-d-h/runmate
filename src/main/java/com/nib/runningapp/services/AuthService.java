package com.nib.runningapp.services;

import com.nib.runningapp.dtos.GoogleUserDTO;
import com.nib.runningapp.dtos.UserDTO;

public interface AuthService {
    UserDTO authenticate(GoogleUserDTO googleUserDTO);
}
