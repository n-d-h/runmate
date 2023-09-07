package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.status = true")
    List<Event> findAllActiveEvent();
}
