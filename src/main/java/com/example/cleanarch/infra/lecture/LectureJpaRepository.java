package com.example.cleanarch.infra.lecture;

import com.example.cleanarch.domain.lecture.Lecture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LectureJpaRepository extends CrudRepository<Lecture, Long> {
    @Query("select l from Lecture l join fetch l.options where l.id = :id")
    Optional<Lecture> findById(@Param("id") Long id);

    @Query("select l from Lecture l join fetch l.instructor join fetch l.options o where o.date = :date")
    List<Lecture> findByDate(@Param("date") LocalDate date);
}
