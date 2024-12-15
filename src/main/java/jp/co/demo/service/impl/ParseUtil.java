package jp.co.demo.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.demo.model.SampleBean;

import java.io.IOException;

public class ParseUtil {
    /**
     * <p>[概 要] JSON文字列⇒基本型（JavaBeans）への変換処理（Jackson版）</p>
     * <p>[詳 細] </p>
     * <p>[備 考] </p>
     * @param  jsonStr JSON形式の文字列
     * @return 基本型（JavaBeans）オブジェクト（パラメータがnullの場合はnullを返します。）
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static SampleBean parseJsonToBeanByJackson(String jsonStr)
            throws JsonParseException, JsonMappingException, IOException {
        if(jsonStr == null){
            // パラメータがnullの場合、nullを返します
            return null;
        }

        // Jacksonのマッパーを生成
        ObjectMapper mapper = new ObjectMapper();

        // JavaBeansオブジェクトをJSON文字列へ変換

        return mapper.readValue(jsonStr, SampleBean.class);
    }

    /**
     * <p>[概 要] 基本型（JavaBeans）⇒JSON文字列への変換処理（Jackson版）</p>
     * <p>[詳 細] </p>
     * <p>[備 考] </p>
     * @param  bean JavaBeansオブジェクト
     * @return JSON変換後の文字列（パラメータがnullの場合はnullを返します。）
     * @throws JsonProcessingException
     */
    public static String parseBeanToJsonByJackson(SampleBean bean) throws JsonProcessingException {
        if(bean == null){
            // パラメータがnullの場合、nullを返します
            return null;
        }

        // Jacksonのマッパーを生成
        ObjectMapper mapper = new ObjectMapper();

        // JavaBeansオブジェクトをJSON文字列へ変換

        return mapper.writeValueAsString(bean);
    }
}
