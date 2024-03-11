package com.fugui.AppointmentRegistration.hosipital.service;

import com.fugui.AppointmentRegistration.model.hosp.Department;
import com.fugui.AppointmentRegistration.model.hosp.Schedule;
import com.fugui.AppointmentRegistration.vo.hosp.ScheduleQueryVo;
import org.apache.catalina.util.ErrorPageSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;

import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/3/11 13:12
 * @Version 1.0
 */
public interface ScheduleService {
    //上传排班接口
    void save(Map<String, Object> paramMap);

    //查询排班接口
   Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    //删除排班
    void remove(String hoscode, String hosScheduleId);
}
