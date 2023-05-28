//package com.hp.topic;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConf {
//
//    @Bean("message")
//    public Queue queueMessage(){
//        return new Queue("active");
//    }
//
//    @Bean
//    public TopicExchange exchange(){
//        return new TopicExchange("exchange");
//    }
//
//    @Bean
//    public Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage,TopicExchange topicExchange){
//        return BindingBuilder.bind(queueMessage).to(topicExchange).with("active.seckill");
//    }
//
//
//
//
//}
