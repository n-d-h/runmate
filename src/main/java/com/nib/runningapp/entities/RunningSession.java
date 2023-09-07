package com.nib.runningapp.entities;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_running_session")
public class RunningSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @Column(name = "duration", nullable = false)
    private Double duration;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "route")
    private String route;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User userRunningSession;
}
