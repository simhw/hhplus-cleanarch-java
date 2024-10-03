package com.example.cleanarch.appliction.enrollment;

import com.example.cleanarch.domain.enrollment.Enrollment;
import com.example.cleanarch.domain.enrollment.EnrollmentService;
import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.lecture.LectureOption;
import com.example.cleanarch.domain.lecture.LectureService;
import com.example.cleanarch.domain.lecture.NoAvailableSeats;
import com.example.cleanarch.domain.user.User;
import com.example.cleanarch.domain.user.UserService;
import com.example.cleanarch.interfaces.enrollment.EnrollmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentFacade {

    private final LectureService lectureService;
    private final EnrollmentService enrollmentService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<Enrollment> enrollments(Long userId) {
        // 1. 회원을 조회한다.
        User user = userService.user(userId);

        // 2. 회원별 강의 등록 목록을 조회한다.
        return enrollmentService.enrollments(user);
    }

    @Transactional
    public Enrollment enroll(EnrollmentDto.EnrollmentRequest request) {
        // 1. 회원을 조회한다.
        User user = userService.user(request.getUserId());

        while (true) {
            // 2. 강의 조회 및 옵션 조회
            Lecture lecture = lectureService.lecture(request.getLectureId());
            LectureOption option = lecture.findOption(request.getLectureOptionId());

            try {
                // 3. 좌석 수 차감
                option.chargeSeats(LocalDateTime.now());

                // 4. 강의 등록
                return enrollmentService.enroll(user, lecture, option);

            } catch (OptimisticLockingFailureException e) {
                // 예외 발생 시 다시 시도
                System.out.println("optimistic locking failure, retrying...");
            } catch (NoAvailableSeats e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        throw new RuntimeException("no seats available");
    }
}
