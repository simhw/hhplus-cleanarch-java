package com.example.cleanarch.domain.enrollment;

import com.example.cleanarch.domain.user.User;

import java.util.List;

public interface EnrollmentRepository {
    List<Enrollment> findByUser(User user);
}
