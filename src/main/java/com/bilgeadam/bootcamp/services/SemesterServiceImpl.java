package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.models.Course;
import com.bilgeadam.bootcamp.models.Semester;
import com.bilgeadam.bootcamp.payload.request.SelectedCourseRequest;
import com.bilgeadam.bootcamp.payload.request.SemesterActivateRequest;
import com.bilgeadam.bootcamp.payload.request.SemesterAddRequest;
import com.bilgeadam.bootcamp.payload.response.SemesterResponse;
import com.bilgeadam.bootcamp.repository.CourseRepository;
import com.bilgeadam.bootcamp.repository.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SemesterServiceImpl implements SemesterService{
    final SemesterRepository semesterRepository;
    final CourseRepository courseRepository;

    public SemesterServiceImpl(SemesterRepository semesterRepository, CourseRepository courseRepository) {
        this.semesterRepository = semesterRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public SemesterResponse selectCoursesForSemester(Long semesterId, SelectedCourseRequest selectedCourseRequest) {
        Semester semester = semesterRepository.getById(semesterId);
        Set<Course> courses = courseRepository.getSelectedCourses(selectedCourseRequest.getSelectedCourseIds());
        semester.setCourses(courses);
        semesterRepository.save(semester);
        return new SemesterResponse(semester);
    }

    @Override
    public SemesterResponse addSemester(SemesterAddRequest semesterAddRequest) {
        Semester semester = semesterRepository.save(new Semester(semesterAddRequest.getName()));
        return new SemesterResponse(semester);
    }

    @Override
    public SemesterResponse activateSemester(SemesterActivateRequest semesterActivateRequest) {
        Semester semester = semesterRepository.getById(semesterActivateRequest.getId());
        semester.setActive(semesterActivateRequest.getActive());
        semester = semesterRepository.save(semester);
        return new SemesterResponse(semester);
    }
}
