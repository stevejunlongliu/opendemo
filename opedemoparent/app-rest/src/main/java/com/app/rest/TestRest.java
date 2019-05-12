package com.app.rest;

import com.opendemo.core.common.annotation.CheckLoginAttr;
import com.opendemo.core.common.annotation.OverTimeLogAttr;
import com.opendemo.core.common.annotation.SessionAttr;
import com.opendemo.core.common.config.CommonKeys;
import com.opendemo.core.common.exception.CommonException;
import com.opendemo.core.common.exception.UserInvalidException;
import com.opendemo.core.common.handler.CommonRest;
import com.opendemo.core.common.model.CommonApiResult;
import com.opendemo.core.common.model.UserInfo;
import com.app.model.api.test.T1Entry;
import com.app.model.api.test.T1Result;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("test")
public class TestRest extends CommonRest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "这是测试接口t1")
    @RequestMapping(value = "/t1", method = RequestMethod.POST)
    public CommonApiResult<T1Result> t1(@RequestBody T1Entry model) {
        System.out.print(model.toString());

        CommonApiResult result = new CommonApiResult();

        T1Result data = new T1Result();
        data.setA(model.getA());
        data.setB(model.getB());
        data.setC("这是个新值");

        result.setData(data);
        result.noticeSuccess();

        return result;
    }

    @ApiOperation(value = "这是测试错误接口")
    @RequestMapping(value = "/testerror", method = RequestMethod.POST)
    public CommonApiResult testError(@RequestBody int model) throws CommonException {


        CommonApiResult result = new CommonApiResult();
        switch (model) {
            case 1:
                int i = 1 / 0;//逻辑性异常
                break;
            case 2:
                //业务异常
                throw new UserInvalidException("用户信息失效");
            default:
                throw new CommonException("错误chrome信息");
        }

        result.setData(null);
        result.noticeSuccess();

        return result;
    }

    @ApiOperation(value = "session设置")
    @RequestMapping(value = "/sessionset", method = RequestMethod.POST)
    public CommonApiResult sesseionSet(HttpServletRequest req) {
        CommonApiResult result = new CommonApiResult();
        UserInfo u = new UserInfo();
        u.setUserId("1");
        u.setUserName("userName");
        req.getSession().setAttribute(CommonKeys.SessionUserKey, u);

        UserInfo sessionUser = (UserInfo) req.getSession().getAttribute(CommonKeys.SessionUserKey);

        logger.info("sessionUser:" + sessionUser);
        result.noticeSuccess();

        return result;
    }

    @ApiOperation(value = "获取session")
    @RequestMapping(value = "/getsession", method = RequestMethod.POST)
    @CheckLoginAttr //判断是否有登陆
    public CommonApiResult<UserInfo> getsession(@SessionAttr UserInfo userInfo) {
        CommonApiResult result = new CommonApiResult();

        result.setData(userInfo);

        result.noticeSuccess();

        return result;
    }

    @ApiOperation(value = "超时请求")
    @RequestMapping(value = "/overtime", method = RequestMethod.POST)
    @OverTimeLogAttr(overTimeSeconds = 1)
    public CommonApiResult<UserInfo> overTime(@RequestParam int time, @RequestBody T1Entry entryss) throws InterruptedException {
        CommonApiResult result = new CommonApiResult();

        Thread.sleep(time * 1000);

        result.noticeSuccess();

        return result;
    }

}
