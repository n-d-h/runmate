package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String fullName;
    private String gender;
    private String imageUrl;
    private String password;
    private String phoneNumber;
    private String progress;
    private String role;
    private String username;


}
