package com.fugui.AppointmentRegistration.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fugui.AppointmentRegistration.model.user.UserInfo;
import com.fugui.AppointmentRegistration.vo.user.LoginVo;
import com.fugui.AppointmentRegistration.vo.user.UserAuthVo;
import com.fugui.AppointmentRegistration.vo.user.UserInfoQueryVo;

import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/4/16 11:46
 * @Version 1.0
 */
public interface UserInfoService extends IService<UserInfo> {
    //用户手机号登录
    Map<String, Object> loginUser(LoginVo loginVo);
    //用户认证
    void userAuth(Long userId, UserAuthVo userAuthVo);
    //用户列表（条件查询带分页）
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);
    //用户锁定
    void lock(Long userId, Integer status);
    //用户详情
    Map<String, Object> show(Long userId);
    //认证审批
    void approval(Long userId, Integer authStatus);
}
