package com.bilgeadam.bootcamp.controllers;

import com.bilgeadam.bootcamp.core.exceptions.BootCampException;
import com.bilgeadam.bootcamp.payload.request.LoginRequest;
import com.bilgeadam.bootcamp.payload.request.SignupRequest;
import com.bilgeadam.bootcamp.payload.response.MessageResponse;
import com.bilgeadam.bootcamp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            authService.registerUser(signUpRequest);
        }
        catch (BootCampException bootCampException) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: " + bootCampException.getMessage() + "!"));
        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
