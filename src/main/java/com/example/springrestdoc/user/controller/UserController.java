package com.example.springrestdoc.user.controller;


import com.example.springrestdoc.user.domain.UserInfo;
import com.example.springrestdoc.user.dto.UserRequest;
import com.example.springrestdoc.user.dto.UserResponse;
import com.example.springrestdoc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user", produces = "application/json; charset=UTF8")
    @ResponseBody
    public ResponseEntity<UserResponse> search(@RequestParam(value = "loginId") String loginId) {
        UserResponse user = userService.searchUser(loginId);
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/user/{loginId}", produces = "application/json; charset=UTF8")
    @ResponseBody
    public ResponseEntity<UserResponse> search2(@PathVariable(name = "loginId") String loginId) {
        UserResponse user = userService.searchUser(loginId);
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/user", produces = "application/json; charset=UTF8")
    @ResponseBody
    public ResponseEntity<UserResponse> join(@RequestBody UserRequest userRequest) {
        UserResponse user = userService.createUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
