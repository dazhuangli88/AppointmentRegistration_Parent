package com.fugui.AppointmentRegistration.hosipital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fugui.AppointmentRegistration.common.exception.HospitalException;
import com.fugui.AppointmentRegistration.common.result.ResultCodeEnum;
import com.fugui.AppointmentRegistration.model.hosp.HospitalSet;
import com.fugui.AppointmentRegistration.hosipital.mapper.HospitalSetMapper;
import com.fugui.AppointmentRegistration.vo.order.SignInfoVo;
import org.springframework.stereotype.Service;
import com.fugui.AppointmentRegistration.hosipital.service.HospitalSetService;

/**
 * @Author 富贵
 * @Date 2024/1/9 20:39
 * @Version 1.0
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    //2.根据传递过来的医院编码，查询数据库，查询签名
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }
    //！！！生成挂号订单流程：第三步获取医院签名信息
    //获取医院签名信息
    @Override
    public SignInfoVo getSignInfoVo(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        if(null == hospitalSet) {
            throw new HospitalException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        return signInfoVo;
    }
}
