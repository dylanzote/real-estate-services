package com.zote.user.service.infrastructure.outbound.entities;

import com.zote.user.service.domain.model.ActivityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user_activities")
public class UserActivityEntity {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    private LocalDateTime activityDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    private String description; // Description of the activity
    private String ipAddress;    // IP address from where the activity was performed
    private String deviceInfo;   // Information about the device used

}
