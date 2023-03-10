package com.trianasalesianos.dam.Soccer.Football.user.repository;

import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username);

    Optional<User> findFirstByUsername(String username);



}
