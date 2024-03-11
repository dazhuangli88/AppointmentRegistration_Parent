package com.fugui.AppointmentRegistration.hosipital.repository;

import com.fugui.AppointmentRegistration.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author 笨蛋
 * @Date 2024/3/10 19:54
 * @Version 1.0
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    //上传科室接口
    Department getDepartementByHoscodeAndDepcode(String hoscode, String depcode);
}
