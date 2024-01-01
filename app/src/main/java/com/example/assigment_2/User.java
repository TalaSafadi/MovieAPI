package com.example.assigment_2;

public class User {
    protected String UserName,Email,Password;
    protected boolean rememberMe_flage;

    public User(String userName, String email, String password, boolean rememberMe_flage) {
        UserName = userName;
        Email = email;
        Password = password;
        this.rememberMe_flage = rememberMe_flage;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isRememberMe_flage() {
        return rememberMe_flage;
    }

    public void setRememberMe_flage(boolean rememberMe_flage) {
        this.rememberMe_flage = rememberMe_flage;
    }
}
