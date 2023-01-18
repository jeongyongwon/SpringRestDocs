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


    @RequestMapping(value = "/user/{loginId}", method = RequestMethod.GET ,produces = "application/json; charset=UTF8")
    @ResponseBody
    public ResponseEntity<UserResponse> search2(@PathVariable String loginId
            , @RequestParam String pcStatus
            , @RequestParam String mobileStatus) {
        System.out.println("loginId = " + loginId + ", pcStatus = " + pcStatus + ", mobileStatus = " + mobileStatus);
        UserResponse user = userService.searchUser(loginId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json; charset=UTF8")
    @ResponseBody
    public ResponseEntity<UserResponse> join(@RequestBody UserRequest userRequest) {
        UserResponse user = userService.createUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
