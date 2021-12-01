package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.FacultyRequest;
import com.bilgeadam.bootcamp.payload.response.FacultyResponse;

public interface FacultyService {
    FacultyResponse addFaculty(FacultyRequest facultyRequest);
}
