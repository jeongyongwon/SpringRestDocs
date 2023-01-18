package com.example.springrestdoc.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Long id;

    private String loginId;

    private String email;

    private String statusMsg;

    private String phoneNumber;

    public UserResponse(Long id,String loginId, String email, String phoneNumber, String statusMsg) {
        this.id = id;
        this.loginId = loginId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.statusMsg = statusMsg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
