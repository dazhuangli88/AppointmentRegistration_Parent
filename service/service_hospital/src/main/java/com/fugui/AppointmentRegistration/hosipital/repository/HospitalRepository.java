package com.fugui.AppointmentRegistration.hosipital.repository;

import com.fugui.AppointmentRegistration.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author 富贵
 * @Date 2024/2/29 16:02
 * @Version 1.0
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

     //判断是否存在数据
    Hospital getHospitalByHoscode(String hoscode);

}
