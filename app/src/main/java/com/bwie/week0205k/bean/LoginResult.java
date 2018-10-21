package com.bwie.week0205k.bean;

/**
 * Created by eric on 2018/10/15.
 */

public class LoginResult {
    private boolean isSuccess;
    private String msg;

    public LoginResult() {
    }

    public LoginResult(boolean isSuccess, String msg) {
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
