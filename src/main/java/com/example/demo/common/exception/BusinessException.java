package com.example.demo.common.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -1309775140098249619L;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected String code;
	
	protected Object param;
	
	
	
    public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

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

	public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
    

	public BusinessException(String code, String msg, Object... args) {
		// TODO Auto-generated constructor stub
		super(String.format(msg, args));
		this.code = code;
		this.msg = String.format(msg, args);		
	}
}
