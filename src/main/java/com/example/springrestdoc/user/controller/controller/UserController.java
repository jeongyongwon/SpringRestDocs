package com.example.springrestdoc.user.controller.controller;


import com.example.springrestdoc.user.domain.UserInfo;
import com.example.springrestdoc.user.dto.UserRequest;
import com.example.springrestdoc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = "application/json; charset=UTF8")
    @ResponseBody
    public ResponseEntity<UserInfo> search(@RequestParam String loginId) {
        UserInfo user = userService.searchUser(loginId);
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/users", produces = "application/json; charset=UTF8")
    @ResponseBody
    public ResponseEntity<UserInfo> join(@RequestBody UserRequest userRequest) {
        System.out.println("userRequest 1 = " + userRequest.getLoginId());
        System.out.println("userRequest 2 = " + userRequest.getPwd());
        UserInfo user = userService.createUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
