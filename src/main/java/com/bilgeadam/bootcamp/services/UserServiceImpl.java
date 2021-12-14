package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.User;
import com.bilgeadam.bootcamp.models.Department;
import com.bilgeadam.bootcamp.models.Faculty;
import com.bilgeadam.bootcamp.models.EnumRole;
import com.bilgeadam.bootcamp.models.Role;
import com.bilgeadam.bootcamp.payload.request.DepartmentMemberRequest;
import com.bilgeadam.bootcamp.payload.response.FacultyResponse;
import com.bilgeadam.bootcamp.payload.response.UserResponse;
import com.bilgeadam.bootcamp.payload.request.MemberRequest;
import com.bilgeadam.bootcamp.repository.DepartmentRepository;
import com.bilgeadam.bootcamp.repository.FacultyRepository;
import com.bilgeadam.bootcamp.repository.UserRepository;
import com.bilgeadam.bootcamp.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final FacultyRepository facultyRepository;
    final DepartmentRepository departmentRepository;

    public UserServiceImpl(FacultyRepository facultyRepository, UserRepository userRepository, RoleRepository roleRepository, DepartmentRepository departmentRepository) {
        this.facultyRepository = facultyRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<UserResponse> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Override
    public UserResponse addInstructorToFaculty(Long memberId, MemberRequest memberRequest) {

        User instructor = userRepository.getById(memberId);
        Faculty faculty = facultyRepository.getById(memberRequest.getFacultyId());
        instructor.setFaculty(faculty);
        instructor = userRepository.save(instructor);

        Role instructorRole = roleRepository.findByName(EnumRole.ROLE_INSTRUCTOR).orElseThrow(() -> new RuntimeException("Role is not found."));
        instructor.getRoles().add(instructorRole);
        userRepository.save(instructor);

        return new UserResponse(instructor);
    }

    public UserResponse removeInstructorFromFaculty(Long memberId) {

        User instructor = userRepository.getById(memberId);
        instructor.setFaculty(null);
        instructor = userRepository.save(instructor);
        return  new UserResponse(instructor);
    }


    public UserResponse addInstructorToDepartment(Long memberId, DepartmentMemberRequest departmentMemberRequest) {

        User instructor = userRepository.getById(memberId);
        Department department = departmentRepository.getById(departmentMemberRequest.getDepartmentId());
        instructor.setDepartment(department);
        instructor = userRepository.save(instructor);

        Role instructorRole = roleRepository.findByName(EnumRole.ROLE_INSTRUCTOR).orElseThrow(() -> new RuntimeException("Role is not found."));
        instructor.getRoles().add(instructorRole);
        userRepository.save(instructor);

        return new UserResponse(instructor);
    }

}
