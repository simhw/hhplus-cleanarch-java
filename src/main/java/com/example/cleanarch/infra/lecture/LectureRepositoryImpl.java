package com.example.cleanarch.infra.lecture;

import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.lecture.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

     private final LectureJpaRepository lectureJpaRepository;

    @Override
    public Lecture findById(Long id) {
        Optional<Lecture> lecture = lectureJpaRepository.findById(id);
        return lecture.orElse(null);
    }

    @Override
    public List<Lecture> findByDate(LocalDate date) {
        return lectureJpaRepository.findByDate(date);
    }
}
