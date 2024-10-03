package com.example.cleanarch.domain.enrollment;

import com.example.cleanarch.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public List<Enrollment> enrollments(User user) {
        return enrollmentRepository.findByUser(user);
    }
}
