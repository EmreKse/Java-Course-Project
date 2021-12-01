package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.core.exceptions.BootCampException;
import com.bilgeadam.bootcamp.payload.request.LoginRequest;
import com.bilgeadam.bootcamp.payload.request.SignupRequest;
import com.bilgeadam.bootcamp.payload.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    void registerUser(SignupRequest signUpRequest) throws BootCampException;
}
