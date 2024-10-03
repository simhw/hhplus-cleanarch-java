package com.example.cleanarch.domain.user;

import com.example.cleanarch.domain.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    protected User() {
    }

    public User(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
