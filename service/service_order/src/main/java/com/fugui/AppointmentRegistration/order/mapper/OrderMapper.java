package com.fugui.AppointmentRegistration.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fugui.AppointmentRegistration.model.order.OrderInfo;
import com.fugui.AppointmentRegistration.vo.order.OrderCountQueryVo;
import com.fugui.AppointmentRegistration.vo.order.OrderCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 富贵
 * @Date 2024/4/23 11:16
 * @Version 1.0
 */
public interface OrderMapper extends BaseMapper<OrderInfo> {
    //查询预约统计数据的方法
    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);
}
