package com.nib.runningapp.repositories;

import com.nib.runningapp.entities.Event;
import com.nib.runningapp.entities.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    @Query("SELECT e FROM Event e WHERE e.status = true AND e.id IN (" +
            "SELECT er.eventContent.id FROM EventRegistration er " +
            "WHERE er.userEvent.id = :userId AND er.status = true)" +
            "ORDER BY e.startTime ASC")
    List<Event> findAllActiveEventRegisteredByUser(Long userId);

    @Query("SELECT er FROM EventRegistration er WHERE " +
            "er.userEvent.id = :userId AND er.eventContent.id = :eventId")
    EventRegistration findByUserIdAndEventId(Long userId, Long eventId);
}
