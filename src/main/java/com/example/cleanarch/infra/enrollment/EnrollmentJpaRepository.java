package com.example.cleanarch.infra.enrollment;

import com.example.cleanarch.domain.enrollment.Enrollment;
import com.example.cleanarch.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrollmentJpaRepository extends CrudRepository<Enrollment, Long> {
    List<Enrollment> findByUser(User user);
}
