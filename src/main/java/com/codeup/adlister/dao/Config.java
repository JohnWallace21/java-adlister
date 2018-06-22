package com.codeup.adlister.dao;

public class Config {
    private String user = "adlister_user";
    private String password = "password";
    private String url ="jdbc:mysql://localhost/adlister_db?serverTimezone=UTC&useSSL=false";;

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }


}


