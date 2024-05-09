package com.fugui.AppointmentRegistration.user.client;
import com.fugui.AppointmentRegistration.model.user.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author 富贵
 * @Date 2024/4/23 12:02
 * @Version 1.0
 */
@FeignClient(value = "service-user")
@Repository
public interface PatientFeignClient {
    //根据就诊人id获取就诊人信息
    @GetMapping("/api/user/patient/inner/get/{id}")
    public Patient getPatientOrder(@PathVariable("id") Long id);
}
