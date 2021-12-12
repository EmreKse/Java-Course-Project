package com.bilgeadam.bootcamp.payload.request;

import javax.validation.constraints.NotNull;

public class FacultyDeanAssignmentRequest {
    @NotNull
    private Long deanId;

    public Long getDeanId() {
        return deanId;
    }

    public void setDeanId(Long deanId) {
        this.deanId = deanId;
    }
}

