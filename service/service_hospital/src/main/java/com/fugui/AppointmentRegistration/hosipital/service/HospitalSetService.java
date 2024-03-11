package com.fugui.AppointmentRegistration.hosipital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fugui.AppointmentRegistration.model.hosp.HospitalSet;

/**
 * @Author 富贵
 * @Date 2024/1/9 20:34
 * @Version 1.0
 */
public interface HospitalSetService extends IService<HospitalSet> {
    //2.根据传递过来的医院编码，查询数据库，查询签名
    String getSignKey(String hoscode);
}
