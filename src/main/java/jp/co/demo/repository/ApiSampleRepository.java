package jp.co.demo.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiSampleRepository {
    ApiSampleModel findById(Integer id);

    void insertTopic(ApiSampleModel model);

    void updateTopic(ApiSampleModel model);

    void deleteTopic(Integer id);
}
