package com.lv.mall.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author 17324
 */

@Configuration
public class MyRabbitDesign {
    @Resource
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate(){

        // ConfirmCallback
        // broker收到就会调用确认回调
        // 只要消息抵达代理那么ack = true
        // 消息正确抵达队列就会触发 returnCallBack
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("conform..."+correlationData+"    ack: " + b);
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {

//            /**
//             * 只要消息没有 投递给指定的队列就会触发这个回调
//             * @param message 投递失败的消息信息
//             * @param replyCode 回复状态码
//             * @param replyText 回复的文本内容
//             * @param exchange 交换机
//             * @param routingKey 路由键
//             */
//
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//
//                System.out.println("失败消息" + message);
//                RabbitTemplate.ReturnsCallback.super.returnedMessage(message, replyCode, replyText, exchange, routingKey);
//            }

            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println(returnedMessage);
            }
        });

    }



}
