package com.nib.runningapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowDTO {
    private Long id;
    private Long followerId;
    private String followerName;
    private Long followingId;
    private String followingName;
}
