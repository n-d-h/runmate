package com.nib.runningapp.mappers;

import com.nib.runningapp.dtos.FollowDTO;
import com.nib.runningapp.entities.Follow;
import com.nib.runningapp.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FollowMapper {

    FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);

    @Mapping(target = "followerId", source = "userFollower.id")
    @Mapping(target = "followerName", source = "userFollower.username")
    @Mapping(target = "followingId", source = "userFollowing.id")
    @Mapping(target = "followingName", source = "userFollowing.username")
    FollowDTO toDTO(Follow follow);

    @Mapping(target = "userFollower", source = "followerId", qualifiedByName = "mapUserFollower")
    @Mapping(target = "userFollowing", source = "followingId", qualifiedByName = "mapUserFollowing")
    @Mapping(target = "status", ignore = true)
    Follow toEntity(FollowDTO followDTO);

    @Named("mapUserFollower")
    default User mapUserFollower(Long id) {
        User f = new User();
        f.setId(id);
        return f;
    }

    @Named("mapUserFollowing")
    default User mapUserFollowing(Long id) {
        User u = new User();
        u.setId(id);
        return u;
    }
}
