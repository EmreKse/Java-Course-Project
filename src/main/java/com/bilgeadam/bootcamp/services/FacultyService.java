package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.*;
import com.bilgeadam.bootcamp.payload.response.FacultyResponse;
import com.bilgeadam.bootcamp.payload.response.UserResponse;

import java.util.List;

public interface FacultyService {
    FacultyResponse addFaculty(FacultyRequest facultyRequest);
    List<FacultyResponse> listFaculties();
    FacultyResponse updateFaculty(Long facultyId, FacultyRequest facultyRequest);
    Void deleteFacultyById(Long facultyId);

    FacultyResponse assignDeanToFaculty(Long facultyId, FacultyDeanAssignmentRequest facultyDeanAssignmentRequest);

    UserResponse addInstructorToFaculty(Long facultyId, MemberRequest memberRequest);
    UserResponse removeInstructorFromFaculty(MemberRequest memberRequest);

}
