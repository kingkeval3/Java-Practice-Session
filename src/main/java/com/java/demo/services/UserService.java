package com.java.demo.services;

import com.java.demo.datastore.models.UserModel;
import com.java.demo.datastore.models.UserModel;
import com.java.demo.exceptions.DataException;

import java.util.List;
import java.util.Map;

public interface UserService {

    String introduce( Map<String,Object> payload);

    List<?> fetchUsers();

    UserModel addUser(UserModel userModel) throws DataException;

}
