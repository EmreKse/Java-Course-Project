package com.bilgeadam.bootcamp.payload.request;

import javax.validation.constraints.NotBlank;

public class FacultyRequest {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

