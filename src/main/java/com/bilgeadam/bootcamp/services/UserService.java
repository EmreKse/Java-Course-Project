package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.MemberRequest;
import com.bilgeadam.bootcamp.payload.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> listUsers();
}
