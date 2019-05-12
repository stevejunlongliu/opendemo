package com.opendemo.core.common.exception;

import com.opendemo.core.common.config.ApiResponseCode;

public class UserInvalidException extends CommonException {

    //todo 初始化的方式不够优雅
    private ApiResponseCode code = ApiResponseCode.user_info_invalid;

    private String msg=ApiResponseCode.user_info_invalid.getDesc();


    public UserInvalidException(String msg) {
        super( msg);
        this.msg = msg;
    }

    @Override
    public ApiResponseCode getCode() {
        return code;
    }

    @Override
    public void setCode(ApiResponseCode code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
