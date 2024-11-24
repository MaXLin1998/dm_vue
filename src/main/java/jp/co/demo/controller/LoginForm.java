package jp.co.demo.controller;

import lombok.Data;

@Data
public class LoginForm {
    private String email;

    private String password;

    private String role;
}
