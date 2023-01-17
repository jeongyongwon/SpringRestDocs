package com.example.springrestdoc;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User createUser(UserRequest userRequest) {

        User user = new User();

        Long id = userRequest.getId();
        String pwd = userRequest.getPwd();
        String email = userRequest.getEmail();
        String phone = "010-5090-7845";

        user.setId(id);
        user.setPwd(pwd);
        user.setEmail(email);
        user.setPhoneNumber(phone);

        return user;
    }
}
