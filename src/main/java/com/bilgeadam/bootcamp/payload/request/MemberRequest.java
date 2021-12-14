package com.bilgeadam.bootcamp.payload.request;

public class MemberRequest {

    private Long FacultyId;
    private Long DepartmentId;

    public Long getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Long departmentId) {
        DepartmentId = departmentId;
    }

    public Long getFacultyId() {
        return FacultyId;
    }

    public void setFacultyId(Long facultyId) {
        FacultyId = facultyId;
    }

}
