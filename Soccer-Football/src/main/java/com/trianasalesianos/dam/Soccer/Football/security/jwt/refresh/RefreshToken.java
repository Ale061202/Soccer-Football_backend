package com.trianasalesianos.dam.Soccer.Football.security.jwt.refresh;

import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class RefreshToken {

    @Id
    private UUID id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id", columnDefinition = "uuid")
    private User user;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @CreatedDate
    private Instant createdAt;
}
