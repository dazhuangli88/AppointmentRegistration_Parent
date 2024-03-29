package com.fugui.AppointmentRegistration.hosipital.repository;

import com.fugui.AppointmentRegistration.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author 富贵
 * @Date 2024/3/11 12:53
 * @Version 1.0
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    //根据医院编号 和 排班编号查询
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
