package com.fugui.AppointmentRegistration.hosipital.service;

import com.fugui.AppointmentRegistration.model.hosp.Hospital;
import com.fugui.AppointmentRegistration.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/2/29 16:16
 * @Version 1.0
 */
public interface HospitalService {
    //上传医院接口
    void save(Map<String, Object> paramMap);

    //实现根据医院编号查询
    Hospital getByHoscode(String hoscode);

    //医院列表（条件查询分页）
    Page<Hospital> selectHospPage(Integer page,Integer limit, HospitalQueryVo hospitalQueryVo);
    //更新医院上线状态
    void updateStatus(String id, Integer status);
    //医院详情信息
    Map<String, Object> getHospById(String id);
    //获取医院名称
    String getHospName(String hoscode);
    //api根据医院名称模糊查询
    List<Hospital> findByHosname(String hosname);

    //api根据医院编号获取医院预约挂号详情
    Map<String, Object> item(String hoscode);
}
