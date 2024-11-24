package jp.co.demo.controller;

import lombok.Data;

@Data
public class RespInfo {
    private int status;

    private String email;

    private String password;

    private String role;

    private String token;
}
