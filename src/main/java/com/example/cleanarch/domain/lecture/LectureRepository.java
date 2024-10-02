package com.example.cleanarch.domain.lecture;

import java.time.LocalDate;
import java.util.List;

public interface LectureRepository {
    Lecture findById(Long id);

    List<Lecture> findByDate(LocalDate date);
}
