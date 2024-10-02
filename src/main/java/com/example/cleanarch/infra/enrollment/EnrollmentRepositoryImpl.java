package com.example.cleanarch.infra.enrollment;

import com.example.cleanarch.domain.enrollment.Enrollment;
import com.example.cleanarch.domain.enrollment.EnrollmentRepository;
import com.example.cleanarch.domain.user.User;
import com.example.cleanarch.domain.user.UserRepository;
import com.example.cleanarch.infra.user.UserJpaRepository;
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
}
