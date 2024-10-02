package com.example.cleanarch.domain.lecture;

import com.example.cleanarch.domain.Instructor;
import com.example.cleanarch.domain.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "lecture")
@AllArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lecture_id")
    private Long id;

    private String title;

    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private List<LectureOption> options = new ArrayList<>();

    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    protected Lecture() {
    }

    public Lecture(Long id, String title, String description, Instructor instructor, List<LectureOption> options) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        setOptions(options);
    }

    private void setOptions(List<LectureOption> options) {
        if (options == null || options.isEmpty())
            throw new RuntimeException("option is empty");
        this.options = options;
    }

    public void verifyEnrollmentAvailable(LocalDateTime now) {
        this.options = this.options.stream()
                .filter(this::isNotFull)
                .filter(option -> this.isNotExpired(option, now))
                .toList();
    }

    public boolean isNotFull(LectureOption option) {
        return option.getSeats() > 0;
    }

    public boolean isNotExpired(LectureOption option, LocalDateTime now) {
        return option.getEndAt().isAfter(now);
    }
}
