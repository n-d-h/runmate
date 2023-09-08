package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.Follow;
import com.nib.runningapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT f FROM Follow f WHERE f.userFollower.id = :userId AND f.userFollowing.id = :userIdToUnfollow")
    Follow findByFollowerIdAndFollowingId(Long userId, Long userIdToUnfollow);

    @Query("SELECT u FROM User u JOIN Follow f ON u.id = f.userFollower.id WHERE f.userFollowing.id = :userId AND f.status = true")
    List<User> findAllFollowersByUserId(Long userId);

    @Query("SELECT u FROM User u JOIN Follow f ON u.id = f.userFollowing.id WHERE f.userFollower.id = :userId AND f.status = true")
    List<User> findAllFollowingsByUserId(Long userId);
}
