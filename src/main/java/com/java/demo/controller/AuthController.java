package com.java.demo.controller;

import com.java.demo.datastore.model.UserModel;
import com.java.demo.datastore.repositories.UserRepository;
import com.java.demo.pojos.AuthenticateRequest;
import com.java.demo.pojos.TokenResponse;
import com.java.demo.services.impl.UserServiceImpl;
import com.java.demo.utils.JwtUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody AuthenticateRequest authenticateRequest){

        return ResponseEntity.ok(
                userRepository.save(
                        new UserModel(
                                authenticateRequest.getUsername(),
                                new BCryptPasswordEncoder().encode(authenticateRequest.getPassword()),
                                "ADMIN", true)));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest authenticateRequest){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getUsername(), authenticateRequest.getPassword()));


        UserDetails userDetails = userServiceImpl.loadUserByUsername(authenticateRequest.getUsername());



        return ResponseEntity.ok(
                new TokenResponse(true, jwtUtils.generateToken(userDetails))
        );
    }

}
