package jp.co.demo.model;

/**
 * <p>[概 要] SampleBean。</p>
 * <p>[詳 細] </p>
 * <p>[備 考] </p>
 * <p>[環 境] </p>
 */

import lombok.Data;

import java.util.List;

@Data
public class SampleBean {

    /**
     * 番号
     */
    private Integer no;

    /**
     * データ
     */
    private String data;

    private List<UserModel> users;

}
