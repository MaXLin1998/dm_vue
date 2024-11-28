package jp.co.demo.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RespInfo {
    private int result_code;
    private String result_error;

    Map<String,Object> result;
}
