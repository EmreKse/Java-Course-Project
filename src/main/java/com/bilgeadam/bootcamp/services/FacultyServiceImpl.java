package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.*;
import com.bilgeadam.bootcamp.payload.request.FacultyDeanAssignmentRequest;
import com.bilgeadam.bootcamp.payload.request.FacultyRequest;
import com.bilgeadam.bootcamp.payload.request.MemberRequest;
import com.bilgeadam.bootcamp.payload.response.FacultyResponse;
import com.bilgeadam.bootcamp.payload.response.UserResponse;
import com.bilgeadam.bootcamp.repository.CourseRepository;
import com.bilgeadam.bootcamp.repository.FacultyRepository;
import com.bilgeadam.bootcamp.repository.RoleRepository;
import com.bilgeadam.bootcamp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService{

    final FacultyRepository facultyRepository;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final CourseRepository courseRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, UserRepository userRepository, RoleRepository roleRepository, CourseRepository courseRepository) {
        this.facultyRepository = facultyRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public FacultyResponse addFaculty(FacultyRequest facultyRequest) {
        Faculty faculty = facultyRepository.save(new Faculty(facultyRequest.getName()));
        return new FacultyResponse(faculty);
    }

    @Override
    public List<FacultyResponse> listFaculties() {
        List<Faculty> faculties = facultyRepository.findAllByIsActive(true);
        return faculties.stream().map(FacultyResponse::new).collect(Collectors.toList());
    }

    @Override
    public FacultyResponse updateFaculty(Long facultyId, FacultyRequest facultyRequest) {
        Faculty faculty = facultyRepository.getById(facultyId);
        faculty.setName(facultyRequest.getName());
        facultyRepository.save(faculty);
        return new FacultyResponse(faculty);
    }

    @Override
    public FacultyResponse assignDeanToFaculty(Long facultyId, FacultyDeanAssignmentRequest facultyDeanAssignmentRequest) {
        Faculty faculty = facultyRepository.getById(facultyId);
        User deanUser = userRepository.getById(facultyDeanAssignmentRequest.getDeanId());
        faculty.setDean(deanUser);
        faculty = facultyRepository.save(faculty);

        Role deanRole = roleRepository.findByName(EnumRole.ROLE_DEAN).orElseThrow(() -> new RuntimeException("Role is not found."));
        deanUser.getRoles().add(deanRole);
        userRepository.save(deanUser);

        return new FacultyResponse(faculty);
    }

    @Override
    public Void deleteFacultyById(Long facultyId) {
        Faculty faculty = facultyRepository.getById(facultyId);
        facultyRepository.deleteById(facultyId);
        return null;
    }

    @Override
    public UserResponse addInstructorToFaculty(Long facultyId, MemberRequest memberRequest) {

        User instructor = userRepository.getById(memberRequest.getMemberId());
        Faculty faculty = facultyRepository.getById(facultyId);
        instructor.setFaculty(faculty);
        instructor = userRepository.save(instructor);

        Role instructorRole = roleRepository.findByName(EnumRole.ROLE_INSTRUCTOR).orElseThrow(() -> new RuntimeException("Role is not found."));
        instructor.getRoles().add(instructorRole);
        userRepository.save(instructor);

        return new UserResponse(instructor);
    }

    @Override
    public UserResponse removeInstructorFromFaculty(MemberRequest memberRequest) {

        User instructor = userRepository.getById(memberRequest.getMemberId());
        instructor.setFaculty(null);
        instructor = userRepository.save(instructor);
        return  new UserResponse(instructor);
    }

}
