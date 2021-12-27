package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.CourseApproveRequest;
import com.bilgeadam.bootcamp.payload.request.CourseInstructorAssignRequest;
import com.bilgeadam.bootcamp.payload.response.CourseResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> listCourseRequests();
    CourseResponse approveOrRejectCourse(Long courseId, CourseApproveRequest courseApproveRequest);
    CourseResponse assignInstructorToCourse(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest);

}

