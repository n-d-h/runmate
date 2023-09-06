package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.RunningSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningSessionRepository extends JpaRepository<RunningSession, Long> {
}
