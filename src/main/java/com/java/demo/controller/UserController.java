package com.java.demo.controller;

import com.java.demo.AbstractRestService;
import com.java.demo.datastore.model.UserModel;
import com.java.demo.datastore.repositories.UserRepository;
import com.java.demo.exceptions.DataException;
import com.java.demo.pojos.UIResponse;
import com.java.demo.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/users")
public class UserController extends AbstractRestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping(value="/introduce")
    public String introduce(@RequestBody Map<String,Object> payload) {
        return userService.introduce(payload);
    }

    @GetMapping(value="/getUsers")
    public List<UserModel> fetchUsers(){
        return (List<UserModel>) userService.fetchUsers();
    }

    @ApiOperation(value = "Create User in Database", notes = "Mandatory fields : username, password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully inserted user", response = UIResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DataException.class),
    })
    @RequestMapping(value="/addUser", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addUser(@RequestBody UserModel newUser) throws DataException{

        try{
            return buildSuccess(userService.addUser(newUser), "successfully inserted user");
        }catch (DataException exception){
            return buildError(exception);
        }
    }

}
