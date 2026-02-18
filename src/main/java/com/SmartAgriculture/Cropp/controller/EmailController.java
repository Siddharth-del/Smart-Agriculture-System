package com.SmartAgriculture.Cropp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SmartAgriculture.Cropp.Service.EmailService;
import com.SmartAgriculture.Cropp.dtos.EmailRequest;
import com.SmartAgriculture.Cropp.model.User;
import com.SmartAgriculture.Cropp.utils.AuthUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    private final AuthUtil authUtils;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody EmailRequest request) {

        emailService.sendAlertEmail(authUtils.loggedInUser().getEmail(), request.getSubject(), request.getBody());
        return ResponseEntity.ok("Alert email sent!");
    }

    @PostMapping("/welcome")
    public ResponseEntity<String> sendWelcome() {
        User user=authUtils.loggedInUser();
        emailService.sendWelcomeEmail(user.getEmail(),user.getUsername());
        return new ResponseEntity<>("Welcome email sent!", HttpStatus.OK);
    }

 
    @PostMapping("/alert")
    public ResponseEntity<String> sendAlert(@RequestBody EmailRequest request) {
        emailService.sendAlertEmail(authUtils.loggedInUser().getEmail(), request.getSubject(), request.getBody());
        return new ResponseEntity<>("Alert email sent!", HttpStatus.OK);
    }
}