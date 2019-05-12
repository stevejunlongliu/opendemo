package com.app.model.api.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "T1输出结果")
public class T1Result {

    @ApiModelProperty(value = "字段a")
    private String a;
    @ApiModelProperty(value = "字段b")
    private String b;

    @ApiModelProperty(value = "字段c")
    private String c;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
