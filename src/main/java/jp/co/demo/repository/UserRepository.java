package jp.co.demo.repository;

import jp.co.demo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {
    List<UserModel> findUsers();

    UserModel findByUserId(String userId);

    void insertTopic(ApiSampleModel model);

    void updateTopic(ApiSampleModel model);

    void deleteTopic(Integer id);
}
