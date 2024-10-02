package com.example.cleanarch.domain.lecture;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureRepository lectureRepository;

    @Test
    @DisplayName("강의 조회 시 잔여 좌석이 1 이상인 강의 옵션만 반환한다.")
    void 강의_조회() {
        // given
        LocalDate now = LocalDate.now();
        Lecture lecture = new Lecture(
                1L,
                "title",
                "description",
                null,
                List.of(
                        new LectureOption(
                                1L,
                                now,
                                LocalDateTime.of(now, LocalTime.of(10, 0, 0)),
                                LocalDateTime.of(now, LocalTime.of(10, 12, 0))
                        ),
                        new LectureOption(
                                2L,
                                now.plusDays(1),
                                LocalDateTime.of(now.plusDays(1), LocalTime.of(10, 0, 0)),
                                LocalDateTime.of(now.plusDays(1), LocalTime.of(12, 0, 0))
                                )
                )
        );

        LocalDate date = LocalDate.of(2024, 10, 2);
        when(lectureRepository.findByDate(date)).thenReturn(List.of(lecture));

        // when
        List<Lecture> lectures = lectureService.lectures(date);

        // then
        Assertions.assertThat(lectures.get(0).getOptions()).size().isEqualTo(1);
    }
}