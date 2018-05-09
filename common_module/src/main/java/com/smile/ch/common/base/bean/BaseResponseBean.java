package com.smile.ch.common.base.bean;

import java.io.Serializable;

/**
 * Author：CHENHAO
 * date：2018/5/3
 * desc：
 */

public class BaseResponseBean<T> implements Serializable {
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
