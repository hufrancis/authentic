package com.wzwl.authentication.enums;

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
