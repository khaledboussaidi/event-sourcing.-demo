package com.CQRS.demo.service;

import com.CQRS.demo.domain.Users;
import com.CQRS.demo.dto.UserDto;

import java.util.List;

public interface UserService {
    Long createUser(UserDto userDto);
    void updateUser(UserDto userDto);
    List<Users> findAll();
}