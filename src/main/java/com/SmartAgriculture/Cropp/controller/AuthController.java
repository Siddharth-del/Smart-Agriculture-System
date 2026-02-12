package com.SmartAgriculture.Cropp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SmartAgriculture.Cropp.Service.AuthService;
import com.SmartAgriculture.Cropp.dtos.RegisterRequest;
import com.SmartAgriculture.Cropp.dtos.RegisterResponse;

@RestController
@RequestMapping("/api")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> createUser(@RequestBody RegisterRequest request){
        RegisterResponse response=authService.createUser(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
