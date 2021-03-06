package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> listUsers();

    List<UserResponse> filterUsersWithFaculty(Long facultyId);
}
