package com.fugui.AppointmentRegistration.hosipital.service;

import com.fugui.AppointmentRegistration.model.hosp.Hospital;

import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/2/29 16:16
 * @Version 1.0
 */
public interface HospitalService {
    //上传医院接口
    void save(Map<String, Object> paramMap);

    //实现根据医院编号查询
    Hospital getByHoscode(String hoscode);
}
