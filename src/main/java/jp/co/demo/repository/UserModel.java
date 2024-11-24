package jp.co.demo.repository;

import lombok.Data;

@Data
public class UserModel {
    private String user_id;
    private String name_last;
    private String name_first;
    private String user_role;
}
