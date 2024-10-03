package com.example.cleanarch.domain.enrollment;

import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.user.User;

import java.util.List;

public interface EnrollmentRepository {
    List<Enrollment> findByUser(User user);

    Enrollment findByUserAndLecture(User user, Lecture lecture);

    Enrollment save(Enrollment enrollment);
}
