package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.GoogleUserDTO;
import com.nib.runningapp.dtos.UserDTO;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.enums.Gender;
import com.nib.runningapp.enums.UserRole;
import com.nib.runningapp.exceptions.entity.EntityNotFoundException;
import com.nib.runningapp.mappers.UserMapper;
import com.nib.runningapp.repositories.UserRepository;
import com.nib.runningapp.security.jwt.JwtService;
import com.nib.runningapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String authenticate(GoogleUserDTO googleUserDTO) {
        var account = userRepository.findByUsernameAndStatus(googleUserDTO.getId(), true);
        if (!account.isPresent()) {
            String username = "";
            if(googleUserDTO.getEmail().contains("@")){
                username = googleUserDTO.getEmail().substring(0, googleUserDTO.getEmail().indexOf("@"));
            }
            else username = googleUserDTO.getEmail();

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

            var roleName = newUser.getRole().name();
            return jwtService.generateToken(Map.of("role", roleName), newUser);
        }
        else{
            var roleName = account.get().getRole().name();
            return jwtService.generateToken(Map.of("role", roleName), account.get());
        }
    }

    @Override
    public Map<String, String> register(String username, String password) {

//        Map<String, String> response = new HashMap<>();
//
//        if (userRepository.findByUsername(username).isPresent()) {
//            response.put("message", "Username already exists");
//            response.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
//            return response;
//        }
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(0L);
//        userDTO.setEmail(null);
//        userDTO.setFullName(null);
//        userDTO.setGender(String.valueOf(Gender.MALE));
//        userDTO.setImageUrl(null);
//        userDTO.setUsername(username);
//        userDTO.setPassword(passwordEncoder.encode(password));
//        userDTO.setPhoneNumber(null);
//        userDTO.setProgress(null);
//        userDTO.setRole(String.valueOf(UserRole.MEMBER));
//
//        User user = UserMapper.INSTANCE.toEntity(userDTO);
//        user.setId(null);
//        user.setStatus(true);
//        userRepository.save(user);
//
//
//        response.put("username", username);
//        response.put("password", password);
//        return response;
        throw new NotImplementedException("Not implemented yet");
    }
}
