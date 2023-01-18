package com.example.springrestdoc.user.service;


import com.example.springrestdoc.user.domain.UserInfo;
import com.example.springrestdoc.user.dto.UserRequest;
import com.example.springrestdoc.user.dto.UserResponse;
import com.example.springrestdoc.user.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse searchUser(String loginId) {
        UserResponse userResponse;

        try {
            UserInfo user = userRepository.findByLoginId(loginId);
            userResponse = new UserResponse(user.getId(),user.getLoginId(), user.getEmail(), user.getPhoneNumber(),"success");
        } catch (NullPointerException e) {
            userResponse = new UserResponse(null,null, null, null,"fail");
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
            userResponse = new UserResponse(null,null, null, null,"fail");
        }

        return userResponse;
    }
}
