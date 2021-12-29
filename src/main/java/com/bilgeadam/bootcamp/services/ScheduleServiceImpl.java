package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.*;
import com.bilgeadam.bootcamp.payload.request.ScheduleRequest;
import com.bilgeadam.bootcamp.payload.response.ScheduledCourseResponse;
import com.bilgeadam.bootcamp.payload.response.ScheduleResponse;
import com.bilgeadam.bootcamp.repository.CourseRepository;
import com.bilgeadam.bootcamp.repository.ScheduleRepository;
import com.bilgeadam.bootcamp.repository.SemesterRepository;
import com.bilgeadam.bootcamp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

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
    public List<ScheduledCourseResponse> getOpenCourses(Long studentId) {
        User student = userRepository.getById(studentId);
        Department department = student.getDepartment();
        Semester activeSemester = semesterRepository.findAllByIsActive(true).get(0);
        List<Schedule> schedules = scheduleRepository.findAllBySemesterAndCourse_Department(activeSemester, department);
        Map<Course, List<Schedule>> scheduleMap = schedules.stream().collect(groupingBy(Schedule::getCourse, mapping(schedule -> schedule, toList())));
        List<ScheduledCourseResponse> scheduledCourseResponses = new ArrayList<>();
        for (Course course : scheduleMap.keySet()) {
            List<Schedule> groupedSchedules = scheduleMap.get(course);
            scheduledCourseResponses.add(new ScheduledCourseResponse(course, groupedSchedules));
        }
        return scheduledCourseResponses;
    }

}
