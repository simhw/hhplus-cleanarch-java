package com.example.cleanarch.interfaces.enrollment;

import lombok.Data;

@Data
public class EnrollmentRequest {
    private Long lectureId;
    private Long lectureOptionId;
    private Long userId;
}
