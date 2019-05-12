package com.opendemo.core.common.exception;

import com.opendemo.core.common.config.ApiResponseCode;


public class CommonException extends Exception {
    private ApiResponseCode code;
    private String msg;

    public CommonException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ApiResponseCode getCode() {
        return code;
    }

    public void setCode(ApiResponseCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
