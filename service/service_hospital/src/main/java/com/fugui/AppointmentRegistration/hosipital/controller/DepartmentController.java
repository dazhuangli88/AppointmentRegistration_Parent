package com.fugui.AppointmentRegistration.hosipital.controller;

import com.fugui.AppointmentRegistration.common.result.Result;
import com.fugui.AppointmentRegistration.hosipital.service.DepartmentService;
import com.fugui.AppointmentRegistration.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 富贵
 * @Date 2024/3/14 15:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/hospital/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //根据医院编号，查询医院所有科室列表
    @ApiOperation(value = "查询医院所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDetList(@PathVariable String hoscode){
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }

}
