package com.java.demo.services.impl;

import com.java.demo.datastore.models.UserModel;
import com.java.demo.datastore.repositories.UserRepository;
import com.java.demo.exceptions.DataException;
import com.java.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserServiceImpl implements UserService {

    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String introduce(Map<String, Object> payload) {
        return name;
    }

    @Override
    public List<?> fetchUsers() {
       return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public UserModel addUser(UserModel userModel) throws DataException {
        return userRepository.save(userModel);
    }
}
