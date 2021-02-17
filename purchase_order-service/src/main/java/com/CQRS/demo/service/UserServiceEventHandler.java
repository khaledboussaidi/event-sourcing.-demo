package com.CQRS.demo.service;

import com.CQRS.demo.domain.Product;
import com.CQRS.demo.domain.User;

public interface UserServiceEventHandler {
    void updateUser(User user);

}
