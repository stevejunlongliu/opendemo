package com.opendemo.core.common.config;

public enum ApiResponseCode {

    success(0, "success"),
    fail(500, "fail"),
    param_error(101, "参数错误"),
    user_info_invalid(201, "用户数据失效"),
    data_not_found(300, "没有找到对应数据"),
    auth_error(202, "没有权限"),
    data_exist(301, "已经存在对应数据");
    private int code;
    private String desc;

    private ApiResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
