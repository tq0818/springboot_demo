package com.example.demo.utils;

import java.io.Serializable;

public class BgyResult implements Serializable {
    private static final long serialVersionUID = 4832771715671880043L;
    private String code;
    private String msg;
    private Object data;

    public BgyResult(){
        this.code = "200";
        this.msg = "SUCCESS";
        this.data = null;
    }

    public BgyResult(String msg) {
        this.code = "400";
        this.msg = msg;
        this.data = null;
    }

    public BgyResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
