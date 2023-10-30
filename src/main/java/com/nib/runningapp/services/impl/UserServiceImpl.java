package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.UserDTO;
import com.nib.runningapp.mappers.UserMapper;
import com.nib.runningapp.repositories.UserRepository;
import com.nib.runningapp.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        var users = userRepository.findAll();
        return users.stream().map(UserMapper.INSTANCE::toDTO).toList();
    }
}
