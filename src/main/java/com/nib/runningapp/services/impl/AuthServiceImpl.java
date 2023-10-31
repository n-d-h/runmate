package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.GoogleUserDTO;
import com.nib.runningapp.dtos.UserDTO;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.enums.Gender;
import com.nib.runningapp.enums.UserRole;
import com.nib.runningapp.mappers.UserMapper;
import com.nib.runningapp.repositories.UserRepository;
import com.nib.runningapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserDTO authenticate(GoogleUserDTO googleUserDTO) {
        var account = userRepository.findByUsernameAndStatus(String.valueOf(googleUserDTO.getId()), true);
        if (!account.isPresent()) {
            String username = "";
            if (googleUserDTO.getEmail().contains("@")) {
                username = googleUserDTO.getEmail().substring(0, googleUserDTO.getEmail().indexOf("@"));
            } else username = googleUserDTO.getEmail();

            UserDTO userDTO = new UserDTO();
            userDTO.setId(googleUserDTO.getId());
            userDTO.setEmail(googleUserDTO.getEmail());
            userDTO.setFullName(googleUserDTO.getDisplayName());
            userDTO.setGender(String.valueOf(Gender.MALE));
            userDTO.setImageUrl(googleUserDTO.getPhotoUrl());
            userDTO.setUsername(username);
            userDTO.setPhoneNumber(null);
            userDTO.setProgress(null);
            userDTO.setRole(String.valueOf(UserRole.MEMBER));

            User user = UserMapper.INSTANCE.toEntity(userDTO);
            user.setStatus(true);
            User newUser = userRepository.save(user);
            UserDTO newUserDTO = UserMapper.INSTANCE.toDTO(newUser);

            return newUserDTO;
        }
        else {
            return UserMapper.INSTANCE.toDTO(account.get());
        }
    }
}
