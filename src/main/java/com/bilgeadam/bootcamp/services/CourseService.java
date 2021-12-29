package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.CourseApproveRequest;
import com.bilgeadam.bootcamp.payload.request.CourseInstructorAssignRequest;
import com.bilgeadam.bootcamp.payload.request.CourseScheduleRequest;
import com.bilgeadam.bootcamp.payload.response.CourseResponse;
import com.bilgeadam.bootcamp.payload.response.OpenCourseInfoResponse;
import com.bilgeadam.bootcamp.payload.response.ScheduleResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> listCourseRequests();
    CourseResponse approveOrRejectCourse(Long courseId, CourseApproveRequest courseApproveRequest);
    CourseResponse assignInstructorToCourse(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest);

    List<CourseResponse> getOpenCourses();

    CourseResponse assignInstructorsToOpenCourses(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest);

    ScheduleResponse assignScheduleToCourse(Long courseId, CourseScheduleRequest courseScheduleRequest);

    OpenCourseInfoResponse getOpenCourseInfo();
}

