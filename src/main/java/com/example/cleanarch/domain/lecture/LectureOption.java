package com.example.cleanarch.domain.lecture;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "lecture_option")
public class LectureOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lecture_option_id")
    private Long id;

    @Description("잔여좌석")
    private Integer seats;

    @Description("시작일")
    private LocalDate date;

    @Description("시작 시간")
    private LocalDateTime startAt;

    @Description("종료 시간")
    private LocalDateTime endAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected LectureOption() {
    }

    public LectureOption(Long id, LocalDate date, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.seats = 30;
        this.date = date;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public boolean isNotFull() {
        return this.seats > 0;
    }

    public boolean isNotExpired(LocalDateTime now) {
        return this.endAt.isAfter(now);
    }

    public void chargeSeats(LocalDateTime now) {
        if (!isNotFull() || !isNotExpired(now)) {
            throw new RuntimeException("is not available lecture");
        }

        seats -= 1;
    }
}
