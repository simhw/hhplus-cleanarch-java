package com.example.cleanarch.appliction.enrollment;

import com.example.cleanarch.domain.enrollment.Enrollment;
import com.example.cleanarch.domain.enrollment.EnrollmentService;
import com.example.cleanarch.domain.lecture.LectureService;
import com.example.cleanarch.domain.user.User;
import com.example.cleanarch.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentFacade {

    private final LectureService lectureService;
    private final EnrollmentService enrollmentService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<Enrollment> lectures(Long userId) {
        // 1. 회원을 조회한다.
        User user = userService.user(userId);

        // 2. 유저별 강의 등록 목록을 조회한다.
        return enrollmentService.enrollments(user);
    }
}
