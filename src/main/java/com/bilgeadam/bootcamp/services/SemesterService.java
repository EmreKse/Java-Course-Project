package com.bilgeadam.bootcamp.services;

import com.bilgeadam.bootcamp.payload.request.SelectedCourseRequest;
import com.bilgeadam.bootcamp.payload.request.SemesterActivateRequest;
import com.bilgeadam.bootcamp.payload.request.SemesterAddRequest;
import com.bilgeadam.bootcamp.payload.response.SemesterResponse;

import java.util.List;

public interface SemesterService {

    SemesterResponse selectCoursesForSemester(Long semesterId, SelectedCourseRequest selectedCourseRequest);
    SemesterResponse addSemester(SemesterAddRequest semesterAddRequest);
    SemesterResponse activateSemester(Long semesterId, SemesterActivateRequest semesterActivateRequest);

    List<SemesterResponse> listSemesters();
}
