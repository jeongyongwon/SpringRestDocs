package com.example.springrestdoc.user.jpa;

import com.example.springrestdoc.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserInfo, Long> {
    UserInfo findByLoginId(String loginId);
}
