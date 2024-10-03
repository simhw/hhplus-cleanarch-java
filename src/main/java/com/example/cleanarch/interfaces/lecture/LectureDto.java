package com.example.cleanarch.interfaces.lecture;

import com.example.cleanarch.domain.Instructor;
import com.example.cleanarch.domain.lecture.Lecture;
import com.example.cleanarch.domain.lecture.LectureOption;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class LectureDto {
    @Data
    public static class LectureResponse {
        private Long id;
        private String title;
        private String description;
        private Instructor instructor;
        private List<LectureOptionResponse> options = new ArrayList<>();

        public LectureResponse(Lecture lecture) {
            this.id = lecture.getId();
            this.title = lecture.getTitle();
            this.description = lecture.getDescription();
            this.instructor = lecture.getInstructor();
            setOptions(lecture.getOptions());
        }

        public void setOptions(List<LectureOption> options) {
            for (LectureOption option : options) {
                this.options.add(new LectureOptionResponse(option));
            }
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
