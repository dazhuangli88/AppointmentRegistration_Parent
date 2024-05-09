package com.fugui.AppointmentRegistration.msm.service;

import com.fugui.AppointmentRegistration.vo.msm.MsmVo;

/**
 * @Author 富贵
 * @Date 2024/4/16 20:27
 * @Version 1.0
 */
public interface MsmService {
    //发送手机验证码
    boolean send(String phone, String code);
    //mq使用发送短信
    boolean send(MsmVo msmVo);

}
