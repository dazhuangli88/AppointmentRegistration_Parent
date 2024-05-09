package com.fugui.AppoointmentRegistration.common.rabbit.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 富贵
 * 消息推送
 * @Date 2024/4/23 16:59
 * @Version 1.0
 */
@Service
public class RabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到rabbitMQ
     * @param exchange 交换机
     * @param routingKey 路由
     * @param message 消息内容
     * @return
     */
    public boolean sendMessage(String exchange,String routingKey,Object message){
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
        return true;
    }
}
