package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.UserDTO;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.enums.Gender;
import com.nib.runningapp.enums.UserRole;
import com.nib.runningapp.exceptions.entity.EntityException;
import com.nib.runningapp.exceptions.entity.EntityNotFoundException;
import com.nib.runningapp.exceptions.entity.QuantityException;
import com.nib.runningapp.mappers.UserMapper;
import com.nib.runningapp.repositories.UserRepository;
import com.nib.runningapp.security.jwt.JwtService;
import com.nib.runningapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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
    public String authenticate(String username, String password) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        var account = userRepository.findByUsernameAndStatus(username, true)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        var roleName = account.getRole().name();
        return jwtService.generateToken(Map.of("role", roleName), account);
    }

    @Override
    public Map<String, String> register(String username, String password) {

        Map<String, String> response = new HashMap<>();

        if (userRepository.findByUsername(username).isPresent()) {
            response.put("message", "Username already exists");
            response.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
            return response;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(0L);
        userDTO.setEmail(null);
        userDTO.setFullName(null);
        userDTO.setGender(String.valueOf(Gender.MALE));
        userDTO.setImageUrl(null);
        userDTO.setUsername(username);
        userDTO.setPassword(passwordEncoder.encode(password));
        userDTO.setPhoneNumber(null);
        userDTO.setProgress(null);
        userDTO.setRole(String.valueOf(UserRole.MEMBER));

        User user = UserMapper.INSTANCE.toEntity(userDTO);
        user.setId(null);
        user.setStatus(true);
        userRepository.save(user);


        response.put("username", username);
        response.put("password", password);
        return response;
    }
}
