package jp.co.demo.service;

import jp.co.demo.repository.UserModel;
import jp.co.demo.repository.UsersModel;
import jp.co.demo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public List<UserModel> findUsers(){
        return usersRepository.findUsers();
    }

    public UserModel findByUserId(String userId){
        return usersRepository.findByUserId(userId);
    }

}
