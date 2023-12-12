package com.grupo11.services;

import com.grupo11.entities.Account;
import com.grupo11.entities.User;
import com.grupo11.entities.dtos.UserDto;
import com.grupo11.exceptions.AccountNotFoundException;
import com.grupo11.exceptions.UserDniDuplicated;
import com.grupo11.exceptions.UserEmailDuplicated;
import com.grupo11.exceptions.UserNotFoundException;
import com.grupo11.mappers.InvestmentMapper;
import com.grupo11.mappers.UserMapper;
import com.grupo11.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<UserDto> getUsers(){
        return repository.findAll().stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con id: " + id));
       // User user = repository.findById(id).get();
        user.setPassword("******");
        return UserMapper.userToDto(user);
    }



    public UserDto createUser(UserDto user){
        User entity = UserMapper.dtoTouser(user);
        if (validateUserByEmail(user) != null) {
            throw new UserEmailDuplicated("Email duplicado");
        }
        if (validateUserByDni(user) != null) {
            throw new UserDniDuplicated("DNI duplicado");
        }
        User entitySaved = repository.save(entity);
        user = UserMapper.userToDto(entitySaved);
        user.setPassword("******");
        return user;
    }

    public String deleteUser(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "El usuario con id: " + id + " ha sido eliminado";
        } else {
            throw new UserNotFoundException("No se encontró el usuario con id: " + id);
        }
    }

    public UserDto updateUser(Long id, UserDto dto) {
        if (repository.existsById(id)){
            User userToModify = repository.findById(id).get();
            // Validar qué datos no vienen en null para setearlos al objeto ya creado

            // Logica del Patch
            if (dto.getUsername() != null){
                userToModify.setUsername(dto.getUsername());
            }

            // TODO: agregar validacion de email existente
            if (dto.getEmail() != null){
                userToModify.setEmail(dto.getEmail());
            }

            if (dto.getPassword() != null){
                userToModify.setPassword(dto.getPassword());
            }

            if (dto.getDni() != null){
                userToModify.setDni(dto.getDni());
            }

            if (dto.getAddress() != null){
                userToModify.setAddress(dto.getAddress());
            }
            if (dto.getBirthdate() != null){
                userToModify.setBirthdate(dto.getBirthdate());
            }

            userToModify.setUpdated_at(LocalDateTime.now());

            User userModified = repository.save(userToModify);

            return UserMapper.userToDto(userModified);
        } else {
            throw new UserNotFoundException("No se encontró el usuario con id: " + id);
        }
    }

    // Validar que existan usuarios unicos por mail
    public User validateUserByEmail(UserDto dto){
        return repository.findByEmail(dto.getEmail());
    }

    public User validateUserByDni(UserDto dto){
        return repository.findByDni(dto.getDni());
    }
}