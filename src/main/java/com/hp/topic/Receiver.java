package com.hp.topic;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component

public class Receiver {

    Logger logger = Logger.getLogger(this.getClass());


    @RabbitListener(queues = "active")
    public void send(Object s){
        logger.info("接收消息"+s);
        logger.info("接收消息时间："+new Date());
    }
}
