package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u WHERE u.email = :username AND u.status = :status")
//    Optional<User> findByUsernameAndStatus(String username, Boolean status);

    Optional<User> findByEmailAndStatus(String email, Boolean status);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPasswordAndStatus(String username,String password, Boolean status);
}
