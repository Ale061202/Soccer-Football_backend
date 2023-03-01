package com.trianasalesianos.dam.Soccer.Football.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.utils.SetRoleAttributeConverter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="user_entity")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(columnDefinition = "uuid")
    private UUID id;

    @NaturalId
    @Column(unique = true, updatable = false)
    private String username;

    private String password;

    private String avatar;

    private String first_name;

    private String last_name;

    private String phone;

    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @Builder.Default
    private boolean accountNonExpired = true;
    @Builder.Default
    private boolean accountNonLocked = true;
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @Builder.Default
    private boolean enabled = true;

    @Convert(converter = SetRoleAttributeConverter.class)
    private Set<UserRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
