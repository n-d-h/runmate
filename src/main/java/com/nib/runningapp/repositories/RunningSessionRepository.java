package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.RunningSession;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningSessionRepository extends JpaRepository<RunningSession, Long> {

    @Query("SELECT rs FROM RunningSession rs " +
            "WHERE rs.userRunningSession.id = :userId " +
            "ORDER BY rs.date DESC, rs.startTime DESC")
    List<RunningSession> findByUserId(Long userId);
}
