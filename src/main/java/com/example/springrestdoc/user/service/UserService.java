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

    public UserInfo searchUser(String loginId) {
        System.out.println("loginId = " + loginId);
        return userRepository.findByLoginId(loginId);
    }

    public UserInfo createUser(UserRequest userRequest) {
        UserInfo user;
        UserResponse userResponse = new UserResponse();
        try {
            user = userRequest.toEntity();
            System.out.println("user 1= " + user.getLoginId());
            System.out.println("user 2= " + user.getPwd());
            userRepository.save(user);
            userResponse.toResponse(user);
            userResponse.setStatusMsg("success");
        } catch (Exception e) {
            userResponse.setStatusMsg("fail");
        }



        return userResponse;
    }
}
