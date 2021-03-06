package com.bilgeadam.bootcamp.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumRole name;

    public Role() {

    }

    public Role(EnumRole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EnumRole getName() {
        return name;
    }

    public void setName(EnumRole name) {
        this.name = name;
    }
}
