package com.fugui.AppointmentRegistration.hosipital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fugui.AppointmentRegistration.common.result.Result;
import com.fugui.AppointmentRegistration.model.hosp.HospitalSet;
import com.fugui.AppointmentRegistration.vo.hosp.HospitalSetQueryVo;
import com.fugui.AppoointmentRegistration.common.util.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fugui.AppointmentRegistration.hosipital.service.HospitalSetService;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

/**
 * @Author 富贵
 * @Date 2024/1/9 20:44
 * @Version 1.0
 */

@Api(tags = "医院设置管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/hospital/hospitalSet")//http://localhost:8003/admin/hospital/hospitalSet/findAll
public class HospitalSetController {
    //注入service
    @Autowired
    private HospitalSetService hospitalSetService;



    //1.查询医院设置表所有信息
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }
    //2.逻辑删除医院设置
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospitalSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if(flag) {
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    //3 条件查询分页
    @ApiOperation(value = "条件查询分页")
    @PostMapping("findPageHospitalSet/{current}/{limit}")
    public Result finPageHospitalSet(@PathVariable long current,
                                      @PathVariable long limit,
                                      @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        //创建对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current,limit);
        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();

        String hosname = hospitalSetQueryVo.getHosname();//医院名称
        String hoscode = hospitalSetQueryVo.getHoscode();//医院编号
        if(!StringUtils.isEmpty (hosname)) {
            wrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if(!StringUtils.isEmpty(hoscode)) {
            wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }

        //调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page,wrapper);

        return Result.ok(pageHospitalSet);



    }


    //4 添加医院设置
    @ApiOperation(value = "添加医院设置")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
        //设置状态 1 使用，0不能使用
        hospitalSet.setStatus(1);

        //签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        //调用service
        boolean add = hospitalSetService.save(hospitalSet);
        if(add) {
            return Result.ok();
        }else{
            return Result.fail();
        }

    }


    //5 根据医院ID获取医院设置
    @ApiOperation(value = "根据医院ID获取医院设置")
    @GetMapping("getHospitalSet/{id}")
    public Result getHospitalSet(@PathVariable Long id){
       // int a = 1/10;
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    //6  修改医院设置
    @ApiOperation(value = "修改医院设置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if(flag) {
            return Result.ok();
        }else {
            return Result.fail();
        }
    }


    //7 批量删除医院设置
    @ApiOperation(value = "批量删除医院设置")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList){
        hospitalSetService.removeByIds(idList);
        return Result.ok();
    }

    //8 医院设置锁定和解锁
    @ApiOperation(value = "医院设置锁定和解锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status){
        //根据id查询医院设置信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //调用方法
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }
    //9 发送签名密钥
    @ApiOperation(value = "发送签名密钥")
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSet(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
       String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return Result.ok();

    }


}

