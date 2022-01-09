package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.CourseApproveRequest;
import com.bilgeadam.bootcamp.payload.request.CourseInstructorAssignRequest;
import com.bilgeadam.bootcamp.payload.response.CourseResponse;
import com.bilgeadam.bootcamp.payload.response.CourseStudentInfo;

import java.util.List;

public interface CourseService {

    List<CourseResponse> listCourseRequests();
    CourseResponse approveOrRejectCourse(Long courseId, CourseApproveRequest courseApproveRequest);
    CourseResponse assignInstructorToCourse(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest);

    List<CourseResponse> getOpenCourses();

    CourseResponse assignInstructorsToOpenCourses(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest);

    CourseStudentInfo getCourseStudentInfo(Long courseId);

    CourseStudentInfo getCourseStudentInfoForInstructor(Long courseId);
}

