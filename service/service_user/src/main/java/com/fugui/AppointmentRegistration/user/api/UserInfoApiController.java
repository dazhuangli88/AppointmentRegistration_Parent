package com.fugui.AppointmentRegistration.user.api;

import com.fugui.AppointmentRegistration.common.result.Result;
import com.fugui.AppointmentRegistration.common.utils.AuthContextHolder;
import com.fugui.AppointmentRegistration.model.user.UserInfo;
import com.fugui.AppointmentRegistration.user.service.UserInfoService;
import com.fugui.AppointmentRegistration.vo.user.LoginVo;
import com.fugui.AppointmentRegistration.vo.user.UserAuthVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/4/16 11:44
 * @Version 1.0
 */

@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {
    @Autowired
    private UserInfoService userInfoService;

    //用户手机号登录接口
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        Map<String,Object> info = userInfoService.loginUser(loginVo);
        return Result.ok(info);
    }


    @PostMapping("auth/userAuth")
    public Result userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request) {
        //其实就是往user_info补充添加一些认证数据进去，根据id查出记录会修改记录
        //传递两个参数，第一个参数用户id，第二个参数认证数据vo对象
        userInfoService.userAuth(AuthContextHolder.getUserId(request),userAuthVo);
        return Result.ok();
    }

    //获取用户id信息接口
    @GetMapping("auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId(request);
        UserInfo userInfo = userInfoService.getById(userId);
        return Result.ok(userInfo);
    }
}
