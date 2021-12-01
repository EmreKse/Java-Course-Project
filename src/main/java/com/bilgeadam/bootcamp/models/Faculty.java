package com.bilgeadam.bootcamp.models;

import com.bilgeadam.bootcamp.core.models.BaseModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "faculties",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Faculty extends BaseModel {

    @NotBlank
    @Size(max = 50)
    private String name;

    public Faculty() {
    }

    public Faculty(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
