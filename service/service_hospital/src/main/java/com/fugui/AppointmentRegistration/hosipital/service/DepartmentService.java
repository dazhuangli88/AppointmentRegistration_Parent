package com.fugui.AppointmentRegistration.hosipital.service;

import com.fugui.AppointmentRegistration.model.hosp.Department;
import com.fugui.AppointmentRegistration.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Author 笨蛋
 * @Date 2024/3/10 19:56
 * @Version 1.0
 */
public interface DepartmentService {
    //上传科室接口
    void save(Map<String, Object> paramMap);

    //查询科室接口
    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    //删除科室接口
    void remove(String hoscode, String depcode);




}
