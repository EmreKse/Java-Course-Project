package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.FacultyDeanAssignmentRequest;
import com.bilgeadam.bootcamp.payload.request.FacultyRequest;
import com.bilgeadam.bootcamp.payload.request.MemberRequest;
import com.bilgeadam.bootcamp.payload.response.FacultyResponse;
import com.bilgeadam.bootcamp.payload.response.MessageResponse;
import com.bilgeadam.bootcamp.payload.response.UserResponse;

import java.util.List;

public interface FacultyService {
    MessageResponse addFaculty(FacultyRequest facultyRequest);
    List<FacultyResponse> listFaculties();
    FacultyResponse updateFaculty(Long facultyId, FacultyRequest facultyRequest);
    Void deleteFacultyById(Long facultyId);

    FacultyResponse assignDeanToFaculty(Long facultyId, FacultyDeanAssignmentRequest facultyDeanAssignmentRequest);

    UserResponse addInstructorToFaculty(Long facultyId, MemberRequest memberRequest);
    UserResponse removeInstructorFromFaculty(MemberRequest memberRequest);
}
