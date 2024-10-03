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
            throw new RuntimeException("no options");
        this.options = options;
    }

    public LectureOption findOption(Long optionId) {
        return options.stream()
                .filter(opt -> opt.getId().equals(optionId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no option"));
    }

    public void verifyEnrollmentAvailable(LocalDateTime now) {
        List<LectureOption> verified = this.options.stream()
                .filter(opt -> opt.isNotFull())
                .filter(opt -> opt.isNotExpired(now))
                .toList();

        this.options.clear();
        this.options.addAll(verified);
    }

}
