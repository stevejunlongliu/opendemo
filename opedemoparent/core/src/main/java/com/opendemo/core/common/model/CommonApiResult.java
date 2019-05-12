package com.opendemo.core.common.model;

//通用输出
public class CommonApiResult<T> {

    //返回结果
    private T data;

    //是否成功
    private boolean success;

    //结果码
    private int code;

    //提示信息
    private String msg;

    //todo错误时修改request的码

    public void noticeSuccess() {
        success = true;
        code = 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CommonApiResult{" +
                "data=" + data +
                ", success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
