package com.fugui.AppointmentRegistration.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fugui.AppointmentRegistration.model.order.OrderInfo;
import com.fugui.AppointmentRegistration.model.order.PaymentInfo;


import java.util.Map;

public interface PaymentService extends IService<PaymentInfo> {

    //向支付记录表添加信息
    void savePaymentInfo(OrderInfo order, Integer status);

    //支付成功   更新订单状态
    void paySuccess(String out_trade_no, Map<String, String> resultMap);

    /**
     * 获取支付记录
     * @param orderId
     * @param paymentType
     * @return
     */
    PaymentInfo getPaymentInfo(Long orderId, Integer paymentType);

}
