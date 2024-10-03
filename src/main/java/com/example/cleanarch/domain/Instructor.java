package com.example.cleanarch.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Entity
@Table(name = "instructor")
@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "instructor_id")
    private Long id;

    private String name;

    private String email;

    protected Instructor() {
    }
}
