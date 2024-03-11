package com.fugui.AppointmentRegistration.hosipital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fugui.AppointmentRegistration.hosipital.repository.DepartmentRepository;
import com.fugui.AppointmentRegistration.hosipital.service.DepartmentService;
import com.fugui.AppointmentRegistration.model.hosp.Department;
import com.fugui.AppointmentRegistration.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/3/10 19:57
 * @Version 1.0
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Map<String, Object> paramMap) {
        //paraMap 转换department对象
        String paraMapString = JSONObject.toJSONString(paramMap);
        Department department = JSONObject.parseObject(paraMapString,Department.class);

        //根据医院编号 和 科室编号查询
        Department departmentExist = departmentRepository.
                getDepartementByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());
        //判断
        if(departmentExist != null){
            departmentExist.setUpdateTime(new Date());
            departmentExist.setIsDeleted(0);
            departmentRepository.save(departmentExist);
        }else{
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);

        }

    }

    @Override
    public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        //创建Pageable对象，设置当前页和每页记录数
        //0s是第一页
        Pageable pageable = PageRequest.of(page-1,limit);
        //创建Example对象
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo,department);
        department.setIsDeleted(0);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example<Department> example = Example.of(department,matcher);

        Page<Department> all = departmentRepository.findAll(example,pageable);
        return all;
    }

    //删除科室接口
    @Override
    public void remove(String hoscode, String depcode) {

        //根据医院编号 和科室编号查询
         Department department = departmentRepository.getDepartementByHoscodeAndDepcode(hoscode,depcode);
         if(department != null){
             //调用方法删除
             departmentRepository.deleteById(department.getId());
         }

    }
}
