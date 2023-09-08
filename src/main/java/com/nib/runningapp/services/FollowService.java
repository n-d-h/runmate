package com.nib.runningapp.services;

import com.nib.runningapp.dtos.FollowDTO;
import com.nib.runningapp.dtos.UserDTO;

import java.util.List;

public interface FollowService {
    FollowDTO follow(Long userId, Long userIdToFollow);
    FollowDTO unfollow(Long userId, Long userIdToUnfollow);
    List<UserDTO> getAllFollowersByUserId(Long userId);
    List<UserDTO> getAllFollowingsByUserId(Long userId);
}
