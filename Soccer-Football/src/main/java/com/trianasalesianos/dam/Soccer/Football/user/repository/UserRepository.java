package com.trianasalesianos.dam.Soccer.Football.user.repository;

import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);


}
