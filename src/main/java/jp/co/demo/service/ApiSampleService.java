package jp.co.demo.service;

import jp.co.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiSampleService {
    private final ApiSampleRepository apiSampleRepository;
    private final UsersRepository usersRepository;

    public List<UserModel> findUsers(){
        return usersRepository.findUsers();
    }

    public UserModel findByUserId(String userId){
        return usersRepository.findByUserId(userId);
    }

    public ApiSampleModel addTopic(String title,String content){
        ApiSampleModel model = new ApiSampleModel(title,content);
        apiSampleRepository.insertTopic(model);
        return model;
    }

    public ApiSampleModel updateTopic(Integer id,String title,String content){
        ApiSampleModel model = new ApiSampleModel(id,title,content);
        apiSampleRepository.updateTopic(model);
        return model;
    }

//    public ApiSampleModel deleteTopic(Integer id){
//        apiSampleRepository.deleteTopic(id);
//        return findById(id);
//    }
}
