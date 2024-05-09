package com.fugui.AppointmentRegistration.hosp.client;

import com.fugui.AppointmentRegistration.vo.hosp.ScheduleOrderVo;
import com.fugui.AppointmentRegistration.vo.order.SignInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author 富贵
 * @Date 2024/4/23 12:45
 * @Version 1.0
 */
@FeignClient(value = "service-hospital")
@Repository
public interface HospitalFeignClient {
    /**
     *根据排班id获取预约下单数据
     */
    @GetMapping("/api/hospital/hospital/inner/getScheduleOrderVo/{scheduleId}")
    ScheduleOrderVo getScheduleOrderVo(@PathVariable("scheduleId") String scheduleId);
    /**
     *获取医院签名信息
     */
    @GetMapping("/api/hospital/hospital/inner/getSignInfoVo/{hoscode}")
    SignInfoVo getSignInfoVo(@PathVariable("hoscode") String hoscode);

}
