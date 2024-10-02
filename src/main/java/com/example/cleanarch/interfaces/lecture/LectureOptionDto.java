package com.example.cleanarch.interfaces.lecture;

import com.example.cleanarch.domain.lecture.LectureOption;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class LectureOptionDto {

    @Data
    public static class LectureOptionResponse {
        private Long id;
        private LocalDate date;
        private LocalDateTime startAt;
        private LocalDateTime endAt;

        public LectureOptionResponse(LectureOption option) {
            this.id = option.getId();
            this.date = option.getDate();
        }
    }
}
