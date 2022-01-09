package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.*;
import com.bilgeadam.bootcamp.payload.request.ScheduleRequest;
import com.bilgeadam.bootcamp.payload.response.MessageResponse;
import com.bilgeadam.bootcamp.payload.response.ScheduledCourseResponse;
import com.bilgeadam.bootcamp.payload.response.ScheduleResponse;
import com.bilgeadam.bootcamp.repository.CourseRepository;
import com.bilgeadam.bootcamp.repository.ScheduleRepository;
import com.bilgeadam.bootcamp.repository.SemesterRepository;
import com.bilgeadam.bootcamp.repository.UserRepository;
import com.bilgeadam.bootcamp.security.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final SemesterRepository semesterRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(SemesterRepository semesterRepository, UserRepository userRepository, CourseRepository courseRepository, ScheduleRepository scheduleRepository) {
        this.semesterRepository = semesterRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponse assignCourseToSchedule(ScheduleRequest scheduleRequest) {
        Semester activeSemester = semesterRepository.findAllByIsActive(true).get(0);
        User selectedInstructor = userRepository.getById(scheduleRequest.getInstructorId());
        Course selectedCourse = courseRepository.getById(scheduleRequest.getCourseId());
        EnumDay selectedDay = EnumDay.getDay(scheduleRequest.getDay());
        //todo check schedule conflict
        Schedule newSchedule = Schedule.builder().semester(activeSemester).instructor(selectedInstructor).course(selectedCourse).day(selectedDay).hour(scheduleRequest.getHour()).build();
        Schedule savedSchedule = scheduleRepository.save(newSchedule);
        return new ScheduleResponse(savedSchedule);
    }

    @Override
    public List<ScheduledCourseResponse> getOpenCourses() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long studentId = ((UserDetailsImpl)authentication.getPrincipal()).getId();

        User student = userRepository.getById(studentId);
        Department department = student.getDepartment();
        Semester activeSemester = semesterRepository.findAllByIsActive(true).get(0);

        List<Schedule> schedules = scheduleRepository.findAllBySemesterAndCourse_Department(activeSemester, department);
        Map<Course, List<Schedule>> scheduleMap = schedules.stream().collect(Collectors.groupingBy(Schedule::getCourse));
        List<ScheduledCourseResponse> scheduledCourseResponses = new ArrayList<>();
        for (Course course : scheduleMap.keySet()) {
            List<Schedule> groupedSchedules = scheduleMap.get(course);
            scheduledCourseResponses.add(new ScheduledCourseResponse(course, groupedSchedules));
        }

        return scheduledCourseResponses;
    }

    @Override
    public ScheduledCourseResponse registerToCourse(Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long studentId = ((UserDetailsImpl)authentication.getPrincipal()).getId();

        User student = userRepository.getById(studentId);

        Course selectedCourse = courseRepository.getById(courseId);
        Set<Course> courseSet = student.getStudentsCourses();
        courseSet.add(selectedCourse);
        student.setStudentsCourses(courseSet);
        userRepository.save(student);

        List<Schedule> scheduleList = new ArrayList<>(selectedCourse.getSchedules());
        return new ScheduledCourseResponse(selectedCourse,scheduleList);
    }

    @Override
    public List<ScheduledCourseResponse> getRegisteredCourses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long studentId = ((UserDetailsImpl)authentication.getPrincipal()).getId();

        User student = userRepository.getById(studentId);
        Department department = student.getDepartment();
        Semester activeSemester = semesterRepository.findAllByIsActive(true).get(0);

        List<Schedule> schedules = scheduleRepository.findAllByCourse_Students(student);
        Map<Course, List<Schedule>> scheduleMap = schedules.stream().collect(Collectors.groupingBy(Schedule::getCourse));
        List<ScheduledCourseResponse> scheduledCourseResponses = new ArrayList<>();
        for (Course course : scheduleMap.keySet()) {
            List<Schedule> groupedSchedules = scheduleMap.get(course);
            scheduledCourseResponses.add(new ScheduledCourseResponse(course, groupedSchedules));
        }

        return scheduledCourseResponses;
    }

    @Override
    public MessageResponse dropCourse(Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long studentId = ((UserDetailsImpl)authentication.getPrincipal()).getId();

        User student = userRepository.getById(studentId);
        Course deletedCourse = courseRepository.getById(courseId);
        Set<Course> courseSet = student.getStudentsCourses();
        courseSet.remove(deletedCourse);
        student.setStudentsCourses(courseSet);
        userRepository.save(student);

        return new MessageResponse(deletedCourse.getName()+" has deleted");
    }

    @Override
    public List<ScheduledCourseResponse> getInstructorsCourses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserDetailsImpl)authentication.getPrincipal()).getId();

        User instructor = userRepository.getById(userId);
        Department department = instructor.getDepartment();
        Semester activeSemester = semesterRepository.findAllByIsActive(true).get(0);

        List<Schedule> schedules = scheduleRepository.findAllByCourse_Instructors(instructor);
        Map<Course, List<Schedule>> scheduleMap = schedules.stream().collect(Collectors.groupingBy(Schedule::getCourse));
        List<ScheduledCourseResponse> scheduledCourseResponses = new ArrayList<>();
        for (Course course : scheduleMap.keySet()) {
            List<Schedule> groupedSchedules = scheduleMap.get(course);
            scheduledCourseResponses.add(new ScheduledCourseResponse(course, groupedSchedules));
        }

        return scheduledCourseResponses;
    }

}
