package jp.co.demo.service;

import jp.co.demo.model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> findUsers();

    UserModel getLoginUser(String username);
}