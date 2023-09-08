package com.nib.runningapp.services.impl;

import com.nib.runningapp.dtos.FollowDTO;
import com.nib.runningapp.dtos.UserDTO;
import com.nib.runningapp.entities.Follow;
import com.nib.runningapp.entities.User;
import com.nib.runningapp.mappers.FollowMapper;
import com.nib.runningapp.mappers.UserMapper;
import com.nib.runningapp.repositories.FollowRepository;
import com.nib.runningapp.services.FollowService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    @Override
    public FollowDTO follow(Long userId, Long userIdToFollow) {
        Follow existingFollow = followRepository.findByFollowerIdAndFollowingId(userId, userIdToFollow);
        if (existingFollow != null) {
            existingFollow.setStatus(true);
            Follow savedFollow = followRepository.save(existingFollow);
            return FollowMapper.INSTANCE.toDTO(savedFollow);
        }
        FollowDTO followDTO = new FollowDTO();
        followDTO.setFollowerId(userId);
        followDTO.setFollowingId(userIdToFollow);

        Follow follow = FollowMapper.INSTANCE.toEntity(followDTO);
        follow.setStatus(true);

        Follow savedFollow = followRepository.save(follow);
        return FollowMapper.INSTANCE.toDTO(savedFollow);
    }

    @Override
    public FollowDTO unfollow(Long userId, Long userIdToUnfollow) {
        Follow existingFollow = followRepository.findByFollowerIdAndFollowingId(userId, userIdToUnfollow);
        existingFollow.setStatus(false);
        Follow savedFollow = followRepository.save(existingFollow);
        return FollowMapper.INSTANCE.toDTO(savedFollow);
    }

    @Override
    public List<UserDTO> getAllFollowersByUserId(Long userId) {
        List<User> followers = followRepository.findAllFollowersByUserId(userId);
        return followers.stream().map(UserMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllFollowingsByUserId(Long userId) {
        List<User> followings = followRepository.findAllFollowingsByUserId(userId);
        return followings.stream().map(UserMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
