package com.fugui.AppointmentRegistration.hosipital.receiver;

import com.fugui.AppointmentRegistration.hosipital.service.ScheduleService;
import com.fugui.AppointmentRegistration.model.hosp.Schedule;
import com.fugui.AppointmentRegistration.vo.msm.MsmVo;
import com.fugui.AppointmentRegistration.vo.order.OrderMqVo;
import com.fugui.AppoointmentRegistration.common.rabbit.constant.MqConst;
import com.fugui.AppoointmentRegistration.common.rabbit.service.RabbitService;
import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HospitalReceiver {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RabbitService rabbitService;
    //订单消费者！
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_ORDER, durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_ORDER),
            key = {MqConst.ROUTING_ORDER}
    ))
    public void receiver(OrderMqVo orderMqVo, Message message, Channel channel) throws IOException {
        if(null != orderMqVo.getAvailableNumber()) {
            //下单成功更新预约数
            Schedule schedule = scheduleService.getScheduleId(orderMqVo.getScheduleId());
            schedule.setReservedNumber(orderMqVo.getReservedNumber());
            schedule.setAvailableNumber(orderMqVo.getAvailableNumber());
            scheduleService.update(schedule);

            //预约成功发送预约成功的短信通知
            MsmVo msmVo = orderMqVo.getMsmVo();
            if(null != msmVo) {
                //短信消息生成者！ 生成短信信息到消息队列
                rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_MSM, MqConst.ROUTING_MSM_ITEM, msmVo);
            }

        } else {
            //取消预约更新预约数
            Schedule schedule = scheduleService.getScheduleId(orderMqVo.getScheduleId());
            int availableNumber = schedule.getAvailableNumber().intValue() + 1;
            schedule.setAvailableNumber(availableNumber);
            scheduleService.update(schedule);

            //取消预约可以做专门发送取消预约的短信通知，这里就不做了！

        }

        //发送短信,放在这里的话你取消订单也会发送预约成功的短信，其实不合理，应该放在上面if里面
    /*  MsmVo msmVo = orderMqVo.getMsmVo();
    if(null != msmVo) {
    //短信消息生成者！ 生成短信信息到消息队列
    rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_MSM, MqConst.ROUTING_MSM_ITEM, msmVo);*/


    }

}
