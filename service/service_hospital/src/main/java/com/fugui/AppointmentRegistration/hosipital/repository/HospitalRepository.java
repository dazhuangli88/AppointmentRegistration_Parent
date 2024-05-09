package com.fugui.AppointmentRegistration.hosipital.repository;

import com.fugui.AppointmentRegistration.model.hosp.Hospital;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 富贵
 * @Date 2024/2/29 16:02
 * @Version 1.0
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {

     //判断是否存在数据
    Hospital getHospitalByHoscode(String hoscode);
    Hospital getHospitalById(String id);

    //前台：根据医院名称查询
    List<Hospital> findHospitalByHosnameLike(String hosname);
}
