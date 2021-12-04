package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.Faculty;
import com.bilgeadam.bootcamp.models.User;
import com.bilgeadam.bootcamp.payload.request.FacultyRequest;
import com.bilgeadam.bootcamp.payload.response.FacultyResponse;
import com.bilgeadam.bootcamp.payload.response.UserResponse;
import com.bilgeadam.bootcamp.repository.FacultyRepository;
import com.bilgeadam.bootcamp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponse(user)).collect(Collectors.toList());
    }

}
