package com.CQRS.demo.service;

import com.CQRS.demo.domain.Users;
import com.CQRS.demo.dto.UserDto;
import com.CQRS.demo.repo.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @Override
    public Long createUser(UserDto userDto) {
        Users user = new Users();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        return this.usersRepository.save(user).getId();
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        this.usersRepository.findById(userDto.getId())
                .ifPresent(user -> {
                    user.setFirstname(userDto.getFirstname());
                    user.setLastname(userDto.getLastname());
                    user.setEmail(userDto.getEmail());
                    this.raiseEvent(userDto);
                });
    }
    
    private void raiseEvent(UserDto dto){
        try{
            String value = OBJECT_MAPPER.writeValueAsString(dto);
            this.kafkaTemplate.sendDefault(dto.getId(), value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}