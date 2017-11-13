package com.innerforge.testapp.requests;

/**
 * Created by LuizH on 08/11/2017.
 */

public class DataWrapper<T>{

    public static final int ERROR_CODE_GENERIC = -1;

    private T data;
    private String message;
    private int code;

    public DataWrapper(T data, String message, int code){
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}