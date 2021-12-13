package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.DepartmentRequest;
import com.bilgeadam.bootcamp.payload.response.DepartmentResponse;


import java.util.List;

public interface DepartmentService {
    DepartmentResponse addDepartment(DepartmentRequest departmentRequest);
    List<DepartmentResponse> listDepartments();
    List<DepartmentResponse> listDepartmentsByFaculty(Long facultyId);
    DepartmentResponse updateDepartment(Long departmentId, DepartmentRequest departmentRequest);
    Void deleteDepartmentById(Long departmentId);
}
