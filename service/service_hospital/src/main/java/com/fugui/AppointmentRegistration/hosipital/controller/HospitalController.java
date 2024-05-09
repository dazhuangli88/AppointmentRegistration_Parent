package com.fugui.AppointmentRegistration.hosipital.controller;

import com.fugui.AppointmentRegistration.common.result.Result;
import com.fugui.AppointmentRegistration.hosipital.service.HospitalService;
import com.fugui.AppointmentRegistration.model.hosp.Hospital;
import com.fugui.AppointmentRegistration.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/3/11 21:39
 * @Version 1.0
 */

@RestController
@RequestMapping("/admin/hospital/hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    //医院列表（条件查询分页）
    @ApiOperation(value = "医院列表（条件查询分页）")
    @GetMapping("findHospList/{page}/{limit}")
    public Result findHospList(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo){
        Page<Hospital> pageModel = hospitalService.selectHospPage(page,limit,hospitalQueryVo);
        return Result.ok(pageModel);
    }

    //更新医院上线状态
    @ApiOperation(value = "更新医院上线状态")
    @GetMapping("updateHospStatus/{id}/{status}")
    public Result updateHospStatus(@PathVariable String id,@PathVariable Integer status){
        hospitalService.updateStatus(id,status);
        return Result.ok();
    }

    //模糊查询医院信息
    @ApiOperation(value = "根据医院名字进行模糊查询医院信息")
    @GetMapping("findByHosName/{id}")
    public Result findByHosName(@PathVariable String id){
        List<Hospital> list = hospitalService.findByHosname(id);
        return Result.ok(list);
    }

    //医院详情信息
    @ApiOperation(value = "医院详情信息")
    @GetMapping("showHospDetail/{id}")
    public Result showHospDetail(@PathVariable String id) {
        Map<String, Object> map = hospitalService.getHospById(id);
        return Result.ok(map);
    }
}
