package com.example.cleanarch.infra.enrollment;

import com.example.cleanarch.domain.enrollment.Enrollment;
import com.example.cleanarch.domain.enrollment.EnrollmentRepository;
import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    @Override
    public List<Enrollment> findByUser(User user) {
        return enrollmentJpaRepository.findByUser(user);
    }

    @Override
    public Enrollment findByUserAndLecture(User user, Lecture lecture) {
        return enrollmentJpaRepository.findByUserAndLecture(user, lecture);
    }

    public Enrollment save(Enrollment enrollment) {
        return enrollmentJpaRepository.save(enrollment);
    }
}
