package com.example.demo.common.exception;

public class ParamException extends RuntimeException{

	private static final long serialVersionUID = -3372366645490034824L;
	
	public static final ParamException INVALID_PARAMETERS_NULL = new ParamException("INVALID_PARAMETERS_NULL","参数异常:传入参数必须不为空！");
	
	public static final ParamException INVALID_PARAMETERS_FORMAT = new ParamException("INVALID_PARAMETERS_FORMAT","参数异常:传入参数格式错误！");
	
	public static final ParamException INVALID_PARAMETERS_TYPE = new ParamException("INVALID_PARAMETERS_FORMAT","参数异常:传入参数类型错误！");
	
	public static final ParamException INVALID_PARAMETERS_SIZE = new ParamException("INVALID_PARAMETERS_SIZE","参数异常:传入参数数量错误！");
	
	
	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected String code;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public ParamException(String code, String msg, Object... args) {
		// TODO Auto-generated constructor stub
		super(String.format(msg, args));
		this.code = code;
		this.msg = String.format(msg, args);		
	}
	
}
