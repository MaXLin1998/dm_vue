package jp.co.demo.repository;

import lombok.Data;

import java.util.List;

@Data
public class UsersModel {
    private List<UserModel> users;

    @Data
    public static class UserModel {
        private String user_id;
        private String name_last;
        private String name_first;
        private String user_role;
    }
}
