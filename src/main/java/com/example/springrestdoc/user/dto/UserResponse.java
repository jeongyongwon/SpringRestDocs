package com.example.springrestdoc.user.dto;


import com.example.springrestdoc.user.domain.UserInfo;

public class UserResponse extends UserInfo {

    private String loginId;
    private String pwd;

    private String email;

    private String phoneNumber;

    private String statusMsg;

    @Override
    public String getLoginId() {
        return loginId;
    }

    @Override
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusMsg() {
        return statusMsg;
    }


    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public void toResponse(UserInfo user) {
        this.loginId = user.getLoginId();
        this.pwd = user.getPwd();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
