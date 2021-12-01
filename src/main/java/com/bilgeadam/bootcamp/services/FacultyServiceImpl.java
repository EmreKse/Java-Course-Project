package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.Faculty;
import com.bilgeadam.bootcamp.payload.request.FacultyRequest;
import com.bilgeadam.bootcamp.payload.response.FacultyResponse;
import com.bilgeadam.bootcamp.repository.FacultyRepository;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService{

    final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public FacultyResponse addFaculty(FacultyRequest facultyRequest) {
        Faculty faculty = facultyRepository.save(new Faculty(facultyRequest.getName()));
        return new FacultyResponse(faculty.getId(), faculty.getName());
    }
}
