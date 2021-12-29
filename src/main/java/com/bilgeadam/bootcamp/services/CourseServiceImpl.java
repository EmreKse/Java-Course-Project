package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.Course;
import com.bilgeadam.bootcamp.models.User;
import com.bilgeadam.bootcamp.payload.request.CourseApproveRequest;
import com.bilgeadam.bootcamp.payload.request.CourseInstructorAssignRequest;
import com.bilgeadam.bootcamp.payload.response.CourseResponse;
import com.bilgeadam.bootcamp.repository.CourseRepository;
import com.bilgeadam.bootcamp.repository.ScheduleRepository;
import com.bilgeadam.bootcamp.repository.SemesterRepository;
import com.bilgeadam.bootcamp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    final CourseRepository courseRepository;
    final UserRepository userRepository;
    final SemesterRepository semesterRepository;
    final ScheduleRepository scheduleRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, SemesterRepository semesterRepository, ScheduleRepository scheduleRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.semesterRepository = semesterRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<CourseResponse> listCourseRequests() {
        List<Course> courseRequests = courseRepository.findAllByIsApproved(false);
        return courseRequests.stream().map(CourseResponse::new).collect(Collectors.toList());
    }

    @Override
    public CourseResponse approveOrRejectCourse(Long courseId, CourseApproveRequest courseApproveRequest) {
        User admin = userRepository.getById(courseApproveRequest.getApprovedOrRejectedById());
        Course course = courseRepository.getById(courseId);
        course.setApprovedOrRejectedBy(admin);
        course.setApproved(courseApproveRequest.isApprove());
        courseRepository.save(course);
        return new CourseResponse(course);
    }

    @Override
    public CourseResponse assignInstructorToCourse(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest) {
        User instructor = userRepository.getById(courseInstructorAssignRequest.getInstructorId());
        Course course = courseRepository.getById(courseId);
        Set<User> instructorList = course.getInstructors();
        instructorList.add(instructor);
        courseRepository.save(course);
        return new CourseResponse(course);
    }

    @Override
    public List<CourseResponse> getOpenCourses() {
        List<Course> openCourses = courseRepository.findAllBySemester_IsActive(true);
        return openCourses.stream().map(CourseResponse::new).collect(Collectors.toList());
    }

    @Override
    public CourseResponse assignInstructorsToOpenCourses(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest) {
        User instructor = userRepository.getById(courseInstructorAssignRequest.getInstructorId());
        Course course = courseRepository.getById(courseId);
        Set<User> instructorList = course.getInstructors();
        instructorList.add(instructor);
        courseRepository.save(course);
        return new CourseResponse(course);
    }
}
