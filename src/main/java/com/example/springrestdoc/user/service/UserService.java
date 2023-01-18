package com.example.springrestdoc.user.service;


import com.example.springrestdoc.user.domain.UserInfo;
import com.example.springrestdoc.user.dto.UserRequest;
import com.example.springrestdoc.user.dto.UserResponse;
import com.example.springrestdoc.user.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse searchUser(String loginId) {
        UserResponse userResponse;

        Optional<UserInfo> user = Optional.ofNullable(userRepository.findByLoginId(loginId));
        if (user.isEmpty()) {
            userResponse = new UserResponse(0L,"fail-id", "fail-Email", "fail-phoneNumber","fail");
        } else {
            UserInfo userData = user.get();
            userResponse = new UserResponse(userData.getId(),userData.getLoginId(), userData.getEmail(), userData.getPhoneNumber(),"success");
        }

        return userResponse;

    }

    public UserResponse createUser(UserRequest userRequest) {
        UserInfo user;
        UserResponse userResponse;
        try {
            user = userRequest.toEntity();
            userRepository.save(user);
            userResponse = new UserResponse(user.getId(),user.getLoginId(), user.getEmail(), user.getPhoneNumber(),"success");
        } catch (Exception e) {
            userResponse = new UserResponse(0L,"fail-id", "fail-Email", "fail-phoneNumber","fail");
        }

        return userResponse;
    }
}
