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

    /**
     * Almacenamos el nuevo usuario con la contarseña
     * cifrada con BCrypt
     * @param newUserDto Datos del nuevo usuario
     * @return Usuario creado

    public Post save(NewUserDto newUserDto) {

    return repository.save(
    User.builder()
    .username(newUserDto.getUsername())
    .password(passwordEncoder.encode(newUserDto.getPassword()))
    .avatar(newUserDto.getAvatar())
    .fullname(newUserDto.getFullname())
    .email(newUserDto.getEmail())
    .build());


    }


     * Se editan solamente algunos datos del usuario.
     * El username, el email y password no se pueden modificar
     * @param editUserDto Nuevo avatar o fullname
     * @return Usuario modificado

    public Post editDetails(Long id, EditUserDto editUserDto) {

    return repository.findById(id)
    .map(user -> {
    user.setAvatar(editUserDto.getAvatar());
    user.setFullname(editUserDto.getFullname());
    return repository.save(user);
    })
    .orElseThrow(() ->new EntityNotFoundException("No user with id: " + id));


    }
     */

    public boolean userExists(String username) {
        return repository.existsByUsername(username);
    }


}
