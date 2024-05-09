package com.fugui.AppointmentRegistration.hosipital.service;

import com.fugui.AppointmentRegistration.model.hosp.Department;
import com.fugui.AppointmentRegistration.model.hosp.Schedule;
import com.fugui.AppointmentRegistration.vo.hosp.ScheduleOrderVo;
import com.fugui.AppointmentRegistration.vo.hosp.ScheduleQueryVo;
import org.apache.catalina.util.ErrorPageSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;

import java.text.ParseException;
import java.util.List;
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

    //根据医院编号和科室编号，查询排班规则数据
    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);
    //根据医院编号、科室编号和工作日期，查询排班详细信息
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);
    //获取可预约排班数据
    Map<String,Object> getBookingScheduleRule(Integer page, Integer limit, String hoscode, String depcode) throws ParseException;

    //获取排班id获取排班数据
    Schedule getScheduleId(String scheduleId);

    //根据排班id获取预约下单数据（！！！生成挂号订单流程：需要就诊人id和排班id，第二步先获取排班id！）
    ScheduleOrderVo getScheduleOrderVo(String scheduleId);

    //更新排班数据 用于mq（mq短信服务）
    void update(Schedule schedule);

}
