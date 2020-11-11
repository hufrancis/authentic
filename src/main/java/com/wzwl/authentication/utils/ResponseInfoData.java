package com.wzwl.authentication.utils;

import lombok.Data;

@Data
public class ResponseInfoData<T> {
	
	private String code;
	
	private String codeDesc;
	
	private String message;
	
	private T data;
	
}
