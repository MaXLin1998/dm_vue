package jp.co.demo.repository;

import lombok.Data;

@Data
public class ApiSampleModel {
    public ApiSampleModel() {
    }
    public ApiSampleModel(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public ApiSampleModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private Integer id;

    private String title;

    private String content;
}
