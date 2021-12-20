package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.CourseApproveRequest;
import com.bilgeadam.bootcamp.payload.response.CourseResponse;


import java.util.List;

public interface CourseService {

    List<CourseResponse> listCourseRequests();

    CourseResponse approveOrRejectCourse(Long courseId, CourseApproveRequest courseApproveRequest);
}

