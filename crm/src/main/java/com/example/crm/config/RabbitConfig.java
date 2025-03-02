package com.example.crm.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.crm.utils.rabbitmq.Constants.*;

@Configuration
public class RabbitConfig {

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean(name = BEAN_SMS_NOTIFICATION_QUEUE)
    public Queue smsNotificationQueue() {
        return new Queue(NAME_SMS_NOTIFICATION_QUEUE);
    }

    @Bean(name = BEAN_NOTIFICATION_TOPIC_EXCHANGE)
    public TopicExchange smsNotificationTopicExchange() {
        return new TopicExchange(NAME_NOTIFICATION_TOPIC_EXCHANGE);
    }


    @Bean
    public Binding bindSmsNotificationQueueToMessageNotificationTopicExchange(
            @Qualifier(BEAN_SMS_NOTIFICATION_QUEUE) Queue queue,
            @Qualifier(BEAN_NOTIFICATION_TOPIC_EXCHANGE) TopicExchange topicExchange) {

        return BindingBuilder.bind(queue).to(topicExchange).with("message.notification.sms");
    }


}
