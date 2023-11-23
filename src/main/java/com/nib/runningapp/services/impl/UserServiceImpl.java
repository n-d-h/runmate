package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.UserCreateDTO;
import com.nib.runningapp.dtos.UserDTO;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.enums.Gender;
import com.nib.runningapp.enums.UserRole;
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

    @Override
    public Boolean createUser(UserCreateDTO userDTO) {
        User user = UserMapper.INSTANCE.createToEntity(userDTO);
        user.setRole(UserRole.MEMBER);
        user.setStatus(true);
        user.setGender(Gender.valueOf(userDTO.getGender().toUpperCase()));
        User newUser = userRepository.save(user);
        if (newUser.getId() != null) {
            return true;
        }
        return null;
    }
}
