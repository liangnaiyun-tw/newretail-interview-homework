package com.example.coupon_system.utils.rabbitmq.producer;

import com.example.coupon_system.persistence.entity.UserCoupon;
import com.example.coupon_system.utils.rabbitmq.dto.UserCouponMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.coupon_system.utils.rabbitmq.Constants.NAME_COUPON_TOPIC_EXCHANGE;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WriteCouponProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendWriteCouponMessage(UserCoupon userCoupon) {
        UserCouponMsg userCouponMsg = new UserCouponMsg();
        userCouponMsg.setRoutingKey("user.coupon.write");
        userCouponMsg.setUserCoupon(userCoupon);
        rabbitTemplate.convertAndSend(NAME_COUPON_TOPIC_EXCHANGE, userCouponMsg.getRoutingKey(), userCouponMsg);
    }
}
