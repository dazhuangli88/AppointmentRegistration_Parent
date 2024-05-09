package com.fugui.AppointmentRegistration.task.scheduled;


import com.fugui.AppoointmentRegistration.common.rabbit.constant.MqConst;
import com.fugui.AppoointmentRegistration.common.rabbit.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTask {

    @Autowired
    private RabbitService rabbitService;


    /**cron表达式，设置执行间隔
     * 每天8点执行 提醒就诊： 0 0 8 * * ？  (注意第7位要删掉，不用，6位就可以了)
     *
     * 每隔2分钟执行：0 0/2 * * * ? :
     * 2021-09-07 19:10:00
     * 2021-09-07 19:12:00
     * 2021-09-07 19:14:00
     * 2021-09-07 19:16:00
     * 2021-09-07 19:18:00
     */
    //@Scheduled(cron = "0 0/2 * * * ?")//但是为了方便测试，我们设置每隔2分钟执行一次！
    @Scheduled(cron = "*/30 6-23 * * *")//8点 - 23点 每一个小时执行一次
    public void taskPatient() {
       // System.out.println("1111");
        //执行的操作是往mq发送短信消息,生成短信信息
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_TASK,MqConst.ROUTING_TASK_8,"111");
    }
}
