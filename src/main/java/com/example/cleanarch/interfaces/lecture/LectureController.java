package com.example.cleanarch.interfaces.lecture;

import com.example.cleanarch.domain.lecture.LectureService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    /***
     * 날짜별로 현재 신청 가능한 특강 목록을 조회
     */
    @GetMapping("")
    public List<LectureDto.LectureResponse> lectures(
            @PathParam(value = "date") LocalDate date
    ) {
        return lectureService.lectures(date).stream()
                .map(LectureDto.LectureResponse::new).toList();
    }
}
