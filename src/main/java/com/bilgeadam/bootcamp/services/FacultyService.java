package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.FacultyRequest;
import com.bilgeadam.bootcamp.payload.response.FacultyResponse;

import java.util.List;

public interface FacultyService {
    FacultyResponse addFaculty(FacultyRequest facultyRequest);
    List<FacultyResponse> listFaculties();
}
