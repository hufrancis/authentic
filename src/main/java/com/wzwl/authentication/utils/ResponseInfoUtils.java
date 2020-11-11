package com.wzwl.authentication.utils;


import com.wzwl.authentication.enums.ResultType;

/**
 * @author huff
 */
public class ResponseInfoUtils {
	public static ResponseInfoData create(String code,String codeDesc,String message) {
		ResponseInfoData resp = new ResponseInfoData<>();
		resp.setCode(code);
		resp.setCodeDesc(codeDesc);
		resp.setMessage(message);
		return resp;
	}
	
	public static <T> ResponseInfoData create(String code,String codeDesc,String message,T data) {
		ResponseInfoData resp = new ResponseInfoData<>();
		resp.setCode(code);
		resp.setCodeDesc(codeDesc);
		resp.setMessage(message);
		resp.setData(data);
		return resp;
	}
	public static ResponseInfoData create(ResultType type , String message) {
		ResponseInfoData resp = new ResponseInfoData<>();
		resp.setCode(type.getCode());
		resp.setCodeDesc(type.getCodeDesc());
		resp.setMessage(message);
		return resp;
	}
	public static <T> ResponseInfoData create(ResultType type ,String message,T data) {
		ResponseInfoData resp = new ResponseInfoData<>();
		resp.setCode(type.getCode());
		resp.setCodeDesc(type.getCodeDesc());
		resp.setMessage(message);
		resp.setData(data);
		return resp;
	}
	public static ResponseInfoData createSuccess() {
		ResponseInfoData resp = new ResponseInfoData<>();
		resp.setCode(ResultType.SUCCESS.getCode());
		resp.setCodeDesc(ResultType.SUCCESS.getCodeDesc());
		resp.setMessage("执行成功");
		return resp;
	}
	public static <T> ResponseInfoData createSuccess(T data) {
		ResponseInfoData resp = new ResponseInfoData<>();
		resp.setCode(ResultType.SUCCESS.getCode());
		resp.setCodeDesc(ResultType.SUCCESS.getCodeDesc());
		resp.setData(data);
		resp.setMessage("执行成功");
		return resp;
	}
	public static ResponseInfoData createFail(String message) {
		ResponseInfoData resp = new ResponseInfoData<>();
		resp.setCode(ResultType.FAIL.getCode());
		resp.setCodeDesc(ResultType.FAIL.getCodeDesc());
		resp.setMessage(message);
		return resp;
	}

}
