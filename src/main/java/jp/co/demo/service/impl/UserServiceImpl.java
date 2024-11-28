package jp.co.demo.service.impl;

import jp.co.demo.model.UserModel;
import jp.co.demo.repository.UserRepository;
import jp.co.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public List<UserModel> findUsers(){
        return userRepository.findUsers();
    }

    @Override
    public UserModel getLoginUser(String username) {
        return userRepository.findByUserId(username);
    }
}
