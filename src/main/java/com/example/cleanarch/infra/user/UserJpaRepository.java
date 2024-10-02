package com.example.cleanarch.infra.user;

import com.example.cleanarch.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<User, Long> {


}
