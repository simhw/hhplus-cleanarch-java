package com.example.cleanarch.domain.enrollment;

import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.lecture.LectureOption;
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

    public Enrollment enroll(User user, Lecture lecture, LectureOption option) {
        Enrollment enrolled = enrollmentRepository.findByUserAndLecture(user, lecture);

        if (enrolled != null) {
            throw new RuntimeException("already enrolled");
        }

        Enrollment enrollment = new Enrollment(user, lecture, option);
        return enrollmentRepository.save(enrollment);
    }
}
