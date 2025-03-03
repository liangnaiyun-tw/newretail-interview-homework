package com.example.coupon_system.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.coupon_system.utils.rabbitmq.Constants.*;

@Configuration
public class RabbitConfig {

    @Bean
    Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name = BEAN_USER_COUPON_WRITE_QUEUE)
    public Queue userCouponWriteQueue() {
        return new Queue(NAME_USER_COUPON_WRITE_QUEUE);
    }

    @Bean(name = BEAN_COUPON_TOPIC_EXCHANGE)
    public TopicExchange couponTopicExchange() {
        return new TopicExchange(NAME_COUPON_TOPIC_EXCHANGE);
    }


    @Bean
    public Binding bindUserCouponWriteQueueToCouponTopicExchange(
            @Qualifier(BEAN_USER_COUPON_WRITE_QUEUE) Queue queue,
            @Qualifier(BEAN_COUPON_TOPIC_EXCHANGE) TopicExchange topicExchange) {

        return BindingBuilder.bind(queue).to(topicExchange).with("user.coupon.write");
    }


}
