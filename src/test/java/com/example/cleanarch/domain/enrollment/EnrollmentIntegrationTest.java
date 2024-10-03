package com.example.cleanarch.domain.enrollment;


import com.example.cleanarch.appliction.enrollment.EnrollmentFacade;
import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.lecture.LectureOption;
import com.example.cleanarch.domain.user.User;
import com.example.cleanarch.infra.lecture.LectureJpaRepository;
import com.example.cleanarch.infra.user.UserJpaRepository;
import com.example.cleanarch.interfaces.enrollment.EnrollmentDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class EnrollmentIntegrationTest {

    @Autowired
    private EnrollmentFacade enrollmentFacade;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private LectureJpaRepository lectureJpaRepository;

    @BeforeEach
    void init() {
        // given
        LocalDate now = LocalDate.now();

        for (int i = 1; i <= 40; i++) {
            User user = new User((long) i, "name" + i, "email", "phone");
            userJpaRepository.save(user);
        }

        Lecture lecture = new Lecture(
                null,
                "title",
                "description",
                null,
                List.of(
                        new LectureOption(
                                null,
                                now.plusDays(1),
                                LocalDateTime.of(now.plusDays(1), LocalTime.of(10, 0, 0)),
                                LocalDateTime.of(now.plusDays(1), LocalTime.of(10, 12, 0))
                        )
                )
        );

        lectureJpaRepository.save(lecture);
    }

    @Test
    @DisplayName("40명의 회원이 동시에 강의 신청 시 30명만 강의 신청에 성공한다.")
    void 강의_등록() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(40);
        CountDownLatch countDownLatch = new CountDownLatch(40);
        AtomicInteger success = new AtomicInteger(0);

        // when
        for (long i = 1; i <= 40; i++) {
            long id = i;
            es.submit(() -> {
                try {
                    enrollmentFacade.enroll(new EnrollmentDto.EnrollmentRequest(id, 1L, 1L));
                    success.incrementAndGet();

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        es.shutdown();

        Assertions.assertThat(success.get()).isEqualTo(30);
    }
}
