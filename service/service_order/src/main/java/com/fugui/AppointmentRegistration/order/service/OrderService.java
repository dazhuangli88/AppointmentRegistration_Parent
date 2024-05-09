package com.fugui.AppointmentRegistration.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fugui.AppointmentRegistration.model.order.OrderInfo;
import com.fugui.AppointmentRegistration.vo.order.OrderCountQueryVo;
import com.fugui.AppointmentRegistration.vo.order.OrderQueryVo;

import java.util.Map;

/**
 * @Author 富贵
 * @Date 2024/4/23 11:08
 * @Version 1.0
 */

public interface OrderService extends IService<OrderInfo> {
    //生成挂号订单
    Long saveOrder(String scheduleId, Long patientId);

    //根据订单id查询订单详情
    OrderInfo getOrder(String orderId);
    //订单列表（条件查询带分页）
    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);
    //取消预约
    Boolean cancelOrder(Long orderId);

    //就诊通知
    void patientTips();

    //预约统计
    Map<String,Object> getCountMap(OrderCountQueryVo orderCountQueryVo );
    /**
     * 订单详情
     * @param orderId
     * @return
     */
    Object show(Long orderId);
}
