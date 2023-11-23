package com.nib.runningapp.services;

import com.nib.runningapp.dtos.UserCreateDTO;
import com.nib.runningapp.dtos.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    Boolean createUser(UserCreateDTO userDTO);
}
