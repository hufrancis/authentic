package com.wzwl.authentication.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespResult {

    private Integer code;
    private String message;
    private Object data;

    public static RespResult success(Object data){

        return new RespResult(200,"request success",data);
    }
}
