package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.EnumRole;
import com.bilgeadam.bootcamp.models.Faculty;
import com.bilgeadam.bootcamp.models.Role;
import com.bilgeadam.bootcamp.models.User;
import com.bilgeadam.bootcamp.payload.request.FacultyDeanAssignmentRequest;
import com.bilgeadam.bootcamp.payload.request.FacultyRequest;
import com.bilgeadam.bootcamp.payload.response.FacultyResponse;
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

    public FacultyServiceImpl(FacultyRepository facultyRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.facultyRepository = facultyRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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


}
