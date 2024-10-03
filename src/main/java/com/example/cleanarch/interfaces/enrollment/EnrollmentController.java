package com.example.cleanarch.interfaces.enrollment;

import com.example.cleanarch.appliction.enrollment.EnrollmentFacade;
import com.example.cleanarch.domain.enrollment.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentFacade enrollmentFacade;

    /**
     * 유저별 신청한 강의 목록 조회
     *
     * @param userId 회원 아이디
     */
    @GetMapping("/user/{userId}")
    public List<EnrollmentDto.EnrollmentResponse> enrollments(@PathVariable(name = "userId") Long userId) {
        List<Enrollment> enrollments = enrollmentFacade.enrollments(userId);
        List<EnrollmentDto.EnrollmentResponse> result = enrollments.stream()
                .map(EnrollmentDto.EnrollmentResponse::new)
                .toList();

        return result;
    }

    /**
     * 강의 신청 생성
     *
     * @param request
     */
    @PostMapping("")
    public Long enroll(@RequestBody EnrollmentDto.EnrollmentRequest request) {
        Enrollment enrollment = enrollmentFacade.enroll(request);
        return enrollment.getId();
    }
}
