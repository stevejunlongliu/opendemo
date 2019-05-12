package com.opendemo.core.common.handler;

import com.opendemo.core.common.config.ApiResponseCode;
import com.opendemo.core.common.exception.CommonException;
import com.opendemo.core.common.model.CommonApiResult;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;

//用于统一处理异常和rest通用变量
public class CommonRest {
    @ExceptionHandler
    @ResponseBody
    public CommonApiResult exceptionHandler(Exception e) {
        return getErrorModel(e);
    }

    static org.slf4j.Logger logger = LoggerFactory.getLogger(CommonRest.class);

    public static CommonApiResult getErrorModel(Exception tr) {
        //todo 想办法把用户信息一起抛出来统一处理
        logger.error("error", tr);
        CommonApiResult result = new CommonApiResult();

        if (tr instanceof UndeclaredThrowableException) {
            tr = (Exception) ((UndeclaredThrowableException) tr).getUndeclaredThrowable();
        }

        if (tr instanceof CommonException) {
            result.setCode(((CommonException) tr).getCode().getCode());
            result.setMsg(((CommonException) tr).getMsg());
        } else {
            //不知道什么问题就报系统异常
            result.setCode(ApiResponseCode.fail.getCode());
            result.setMsg("系统异常");
        }


        return result;

    }
}
