package com.grupo11.mappers;

import com.grupo11.entities.User;
import com.grupo11.entities.dtos.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserMapper {

    public static User dtoTouser(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());
        user.setDni(dto.getDni());
        user.setBirthdate(dto.getBirthdate());
        user.setEmail(dto.getEmail());
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());
        return user;
    }

    public static UserDto userToDto(User user){
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setId(user.getId());
        dto.setAddress(user.getAddress());
        dto.setEmail(user.getEmail());
        dto.setBirthdate(user.getBirthdate());
        dto.setDni(user.getDni());
        dto.setCreated_at(user.getCreated_at());
        dto.setUpdated_at(user.getUpdated_at());
        return dto;
    }
}