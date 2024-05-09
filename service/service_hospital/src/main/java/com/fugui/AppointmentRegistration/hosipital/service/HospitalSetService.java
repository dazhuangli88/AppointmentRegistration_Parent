package com.fugui.AppointmentRegistration.hosipital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fugui.AppointmentRegistration.model.hosp.HospitalSet;
import com.fugui.AppointmentRegistration.vo.order.SignInfoVo;

/**
 * @Author 富贵
 * @Date 2024/1/9 20:34
 * @Version 1.0
 */
public interface HospitalSetService extends IService<HospitalSet> {
    //2.根据传递过来的医院编码，查询数据库，查询签名
    String getSignKey(String hoscode);
    //获取医院签名信息（！！！生成挂号订单流程：第三步获取医院签名信息）
    SignInfoVo getSignInfoVo(String hoscode);
}
