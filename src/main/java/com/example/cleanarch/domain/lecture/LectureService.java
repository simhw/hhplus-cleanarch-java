package com.example.cleanarch.domain.lecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional(readOnly = true)
    public List<Lecture> lectures(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }

        LocalDateTime now = LocalDateTime.now();
        List<Lecture> lectures = lectureRepository.findByDate(date);

        lectures.forEach(lecture -> {
            lecture.verifyEnrollmentAvailable(now);
        });

        return lectures;
    }

    @Transactional(readOnly = true)
    public Lecture lecture(Long id) {
        return lectureRepository.findById(id);
    }
}
