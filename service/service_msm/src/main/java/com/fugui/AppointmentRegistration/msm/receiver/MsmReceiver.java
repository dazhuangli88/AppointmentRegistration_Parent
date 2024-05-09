package com.fugui.AppointmentRegistration.msm.receiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fugui.AppointmentRegistration.msm.service.MsmService;
import com.fugui.AppointmentRegistration.vo.msm.MsmVo;
import com.fugui.AppoointmentRegistration.common.rabbit.constant.MqConst;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MsmReceiver {
    //封装mq监听器
    @Autowired
    private MsmService smsService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_MSM_ITEM, durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_MSM),
            key = {MqConst.ROUTING_MSM_ITEM}
    ))
    public void send(MsmVo msmVo, Message message, Channel channel){
        log.info("msmVO:{}", msmVo);
        smsService.send(msmVo);
    }
}
