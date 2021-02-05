package com.erxi.ms.util;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/12/31 - 10:09
 */
@Component
public class AMQPUtil {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    //点对点
    public void createExchange(String exchange_name) {
        amqpAdmin.declareExchange(new DirectExchange(exchange_name));
    }

    public void createQueue(String queue, String exchange_name, String routingKey) {
        if (amqpAdmin.getQueueProperties(queue) == null) {
            amqpAdmin.declareQueue(new Queue(queue, true, false, false, null));
        }
        //创建绑定规则        参数：目的地（队列）、绑定的类型（队列）、交换机（Exchange）、路由键（routingKey），Map（没有可以指定为null）
        amqpAdmin.declareBinding(new Binding(queue, Binding.DestinationType.QUEUE,exchange_name,routingKey,null));
    }

    public void sendMessage(String exchange_name, String routingKey, Object object, String wait_time){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration(wait_time); // 设置过期时间，单位：毫秒
        byte[] msgBytes = object.toString().getBytes();
        Message message = new Message(msgBytes, messageProperties);
        rabbitTemplate.convertAndSend(exchange_name, routingKey, message);
    }

    public void receiveMessage(String queue){
        Object o = rabbitTemplate.receiveAndConvert(queue);
        System.out.println(o.getClass());
        System.out.println(o);
    }
}