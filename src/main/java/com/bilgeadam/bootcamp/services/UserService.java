package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.DepartmentRequest;
import com.bilgeadam.bootcamp.payload.request.MemberRequest;
import com.bilgeadam.bootcamp.payload.response.DepartmentResponse;
import com.bilgeadam.bootcamp.payload.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> listUsers();
    UserResponse addInstructorToFaculty(Long memberId, MemberRequest memberRequest);
    UserResponse removeInstructorFromFaculty(Long memberId);
    UserResponse addInstructorToDepartment(Long memberId, MemberRequest memberRequest);
}
