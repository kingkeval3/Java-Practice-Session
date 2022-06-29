package com.java.demo.services.impl;

import com.java.demo.datastore.model.UserModel;
import com.java.demo.datastore.repositories.UserRepository;
import com.java.demo.exceptions.DataException;
import com.java.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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
       return userRepository.findAll();
    }

    @Override
    public UserModel addUser(UserModel userModel) throws DataException {

        if(userModel.getAge()==null){
            throw new DataException("Age cannot be null", HttpStatus.BAD_REQUEST);
        }

        return userRepository.save(userModel);
    }


}
