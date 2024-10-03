package com.example.cleanarch.domain.enrollment;

import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.lecture.LectureOption;
import com.example.cleanarch.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "enrollment_id")
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_option_id")
    private LectureOption option;

    protected Enrollment() {
    }

    public Enrollment(Long id, User user, Lecture lecture, LectureOption option) {
        this.id = id;
        this.user = user;
        this.lecture = lecture;
        this.option = option;
    }

    public Enrollment(User user, Lecture lecture, LectureOption option) {
        this.user = user;
        this.lecture = lecture;
        this.option = option;
    }
}
