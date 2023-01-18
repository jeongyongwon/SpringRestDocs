package com.example.springrestdoc.user.dto;

import com.example.springrestdoc.user.domain.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    private String loginId;

    private String pwd;

    private String email;

    private String phoneNumber;

    public UserInfo toEntity() {
        UserInfo user = new UserInfo();

        user.setLoginId(getLoginId());
        user.setPwd(getPwd());
        user.setEmail(getEmail());
        user.setPhoneNumber(getPhoneNumber());

        return user;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
