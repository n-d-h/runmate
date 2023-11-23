package com.nib.runningapp.dtos;

import com.nib.runningapp.enums.Gender;
import com.nib.runningapp.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String gender;

    private String phoneNumber;

    private String imageUrl;

    private String progress;

}
