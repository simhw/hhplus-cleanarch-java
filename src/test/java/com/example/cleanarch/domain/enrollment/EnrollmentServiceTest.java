package com.example.cleanarch.domain.enrollment;


import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.lecture.LectureOption;
import com.example.cleanarch.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    LocalDate now = LocalDate.now();

    Lecture lecture;
    LectureOption option1;
    LectureOption option2;
    User user;

    @BeforeEach
    void init() {
        // given
        option1 = new LectureOption(
                1L,
                now.plusDays(1),
                LocalDateTime.of(now.plusDays(1), LocalTime.of(10, 0, 0)),
                LocalDateTime.of(now.plusDays(1), LocalTime.of(12, 0, 0))
        );
        option2 = new LectureOption(
                2L,
                now.plusDays(7),
                LocalDateTime.of(now.plusDays(7), LocalTime.of(10, 0, 0)),
                LocalDateTime.of(now.plusDays(7), LocalTime.of(12, 0, 0))
        );
        lecture = new Lecture(
                1L,
                "title",
                "description",
                null,
                List.of(option1, option2)
        );

        user = new User(
                1L,
                "name",
                "email",
                "phone"
        );

    }

    @Test
    @DisplayName("등록되지 않은 강의 경우 아이디를 반환한다.")
    void 강의_등록() {
        // given
        Enrollment enrollment = new Enrollment(user, lecture, option1);
        when(enrollmentRepository.findByUserAndLecture(user, lecture)).thenReturn(null);
        when(enrollmentRepository.save(any())).thenReturn(enrollment);

        // when
        Long enroll = enrollmentService.enroll(user, lecture, option1).getId();

        // then
        assertThat(enrollment).isNotNull();
    }

    @Test
    @DisplayName("이미 등록한 강의 경우 아이디를 반환에 실패한다.")
    void 강의_등록_중복() {
        // given
        Enrollment enrollment = new Enrollment(user, lecture, option1);
        when(enrollmentRepository.findByUserAndLecture(user, lecture)).thenReturn(enrollment);

        // then
        Assertions.assertThrows(RuntimeException.class, () -> enrollmentService.enroll(user, lecture, option1));
    }
}
