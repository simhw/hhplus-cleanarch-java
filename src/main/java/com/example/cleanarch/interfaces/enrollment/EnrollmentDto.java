package com.example.cleanarch.interfaces.enrollment;

import com.example.cleanarch.domain.Instructor;
import com.example.cleanarch.domain.enrollment.Enrollment;
import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.lecture.LectureOption;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EnrollmentDto {
    @Data
    public static class EnrollmentResponse {
        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LectureResponse lecture;
        private LectureOptionResponse option;

        public EnrollmentResponse(Enrollment enrollment) {
            this.id = enrollment.getId();
            this.createdAt = enrollment.getCreatedAt();
            this.updatedAt = enrollment.getUpdatedAt();
            this.lecture = new LectureResponse(enrollment.getLecture());
            this.option = new LectureOptionResponse(enrollment.getOption());
        }
    }

    @Data
    public static class LectureResponse {
        private Long id;
        private String title;
        private String description;
        private Instructor instructor;

        public LectureResponse(Lecture lecture) {
            this.id = lecture.getId();
            this.title = lecture.getTitle();
            this.instructor = lecture.getInstructor();
        }
    }

    @Data
    public static class LectureOptionResponse {
        private Long id;
        private LocalDate date;
        private LocalDateTime startAt;
        private LocalDateTime endAt;

        public LectureOptionResponse(LectureOption option) {
            this.id = option.getId();
            this.date = option.getDate();
            this.startAt = option.getStartAt();
            this.endAt = option.getEndAt();
        }
    }

}
