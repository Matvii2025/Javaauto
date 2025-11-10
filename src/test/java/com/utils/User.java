package com.utils;

public class User {
    private final String username;
    private final String password;
    public User(String u, String p){ this.username=u; this.password=p; }
    public String username(){ return username; }
    public String password(){ return password; }
    public static User fromConfig(){ return new User(Config.username(), Config.password()); }
}