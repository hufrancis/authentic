package com.wzwl.authentication.enums;

/**
 * @author huff
 * @date 2020/11/11 15:53
 */

public enum ResultType {
    SUCCESS("200","请求成功"),FAIL("500","处理失败");

    private ResultType(String code, String codeDesc) {
        // TODO Auto-generated constructor stub
        this.code = code;
        this.codeDesc = codeDesc;
    }
    protected String code;

    protected String codeDesc;

    public String getCode() {
        return code;
    }

    public String getCodeDesc() {
        return codeDesc;
    }


}

