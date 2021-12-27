package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.SelectedCourseRequest;
import com.bilgeadam.bootcamp.payload.request.SemesterActivateRequest;
import com.bilgeadam.bootcamp.payload.request.SemesterAddRequest;
import com.bilgeadam.bootcamp.payload.response.SemesterResponse;

public interface SemesterService {

    SemesterResponse selectCoursesForSemester(Long semesterId, SelectedCourseRequest selectedCourseRequest);
    SemesterResponse addSemester(SemesterAddRequest semesterAddRequest);
    SemesterResponse activateSemester(SemesterActivateRequest semesterActivateRequest);
}
