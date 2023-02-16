package com.trianasalesianos.dam.Soccer.Football.user.service;


import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import com.trianasalesianos.dam.Soccer.Football.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;


    public List<User> findAll() {

        List<User> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No users with this search criteria");

        return repository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return repository.findById(id);
    }

    public boolean userExists(String username) {
        return repository.existsByUsername(username);
    }


}
