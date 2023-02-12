package com.trianasalesianos.dam.Soccer.Football.repository;

import com.trianasalesianos.dam.Soccer.Football.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
