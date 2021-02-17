package com.CQRS.demo.controller;

import com.CQRS.demo.domain.Users;
import com.CQRS.demo.dto.UserDto;
import com.CQRS.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Long createUser(@RequestBody UserDto userDto){
        return this.userService.createUser(userDto);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody UserDto userDto){
        this.userService.updateUser(userDto);
    }

    @GetMapping("/all")
    List<Users> getAll(){
        return  userService.findAll();
    }


}