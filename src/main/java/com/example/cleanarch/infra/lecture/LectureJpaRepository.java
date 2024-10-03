package com.example.cleanarch.infra.lecture;

import com.example.cleanarch.domain.lecture.Lecture;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface LectureJpaRepository extends CrudRepository<Lecture, Long> {
     List<Lecture> findByOptionsDate(LocalDate date);
}
