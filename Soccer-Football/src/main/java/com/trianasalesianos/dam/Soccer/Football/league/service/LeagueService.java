package com.trianasalesianos.dam.Soccer.Football.league.service;

import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.league.repository.LeagueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueService {
    private final LeagueRepository repository;


    public List<League> findAll() {

        List<League> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No users with this search criteria");

        return repository.findAll();
    }

    public League findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id: " + id));

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

}