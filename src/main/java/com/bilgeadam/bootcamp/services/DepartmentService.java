package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.CourseAddRequest;
import com.bilgeadam.bootcamp.payload.request.DepartmentRequest;
import com.bilgeadam.bootcamp.payload.request.MemberRequest;
import com.bilgeadam.bootcamp.payload.response.CourseResponse;
import com.bilgeadam.bootcamp.payload.response.DepartmentResponse;
import com.bilgeadam.bootcamp.payload.response.UserResponse;


import java.util.List;

public interface DepartmentService {
    DepartmentResponse addDepartment(DepartmentRequest departmentRequest);
    List<DepartmentResponse> listDepartments();
    List<DepartmentResponse> listDepartmentsByFaculty(Long facultyId);
    DepartmentResponse updateDepartment(Long departmentId, DepartmentRequest departmentRequest);
    Void deleteDepartmentById(Long departmentId);

    UserResponse addInstructorToDepartment(Long departmentId, MemberRequest memberRequest);

    CourseResponse addCourseRequest(Long departmentId, CourseAddRequest courseAddRequest);
}
