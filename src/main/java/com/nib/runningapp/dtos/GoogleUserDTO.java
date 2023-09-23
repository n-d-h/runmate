package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUserDTO {
    private String displayName;
    private String email;
    private String id;
    private String photoUrl;
}
