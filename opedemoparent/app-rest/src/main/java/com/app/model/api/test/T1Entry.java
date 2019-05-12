package com.app.model.api.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "T1输入实体")
public class T1Entry {

    @ApiModelProperty(value = "字段a")
    private String a;
    @ApiModelProperty(value = "字段b")
    private String b;

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

    @Override
    public String toString() {
        return "T1Entry{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
