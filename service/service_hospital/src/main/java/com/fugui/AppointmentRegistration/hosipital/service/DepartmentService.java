package com.fugui.AppointmentRegistration.hosipital.service;

import com.fugui.AppointmentRegistration.model.hosp.Department;
import com.fugui.AppointmentRegistration.vo.hosp.DepartmentQueryVo;
import com.fugui.AppointmentRegistration.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
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

    //根据医院编号，查询医院所有科室列表
    List<DepartmentVo> findDeptTree(String hoscode);
    //根据科室编号，和医院编号，查询科室名称
    String getDepName(String hoscode, String depcode);
    //根据科室编号，和医院编号，查询科室对象
    Department getDepartment(String hoscode, String depcode);
}
