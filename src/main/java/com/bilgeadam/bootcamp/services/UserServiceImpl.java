package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.User;
import com.bilgeadam.bootcamp.payload.response.UserResponse;
import com.bilgeadam.bootcamp.repository.UserRepository;
import com.bilgeadam.bootcamp.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserResponse> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> filterUsersWithFaculty(Long facultyId) {
        List<User> users = userRepository.findAllByFacultyId(facultyId);
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

}
