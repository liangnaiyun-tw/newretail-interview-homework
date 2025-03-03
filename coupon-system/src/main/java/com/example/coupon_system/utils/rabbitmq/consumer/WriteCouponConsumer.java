package com.example.coupon_system.utils.rabbitmq.consumer;

import com.example.coupon_system.service.ICouponService;
import com.example.coupon_system.service.IUserCouponService;
import com.example.coupon_system.utils.rabbitmq.dto.UserCouponMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.coupon_system.utils.rabbitmq.Constants.NAME_USER_COUPON_WRITE_QUEUE;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WriteCouponConsumer {

    private final IUserCouponService userCouponService;
    @RabbitListener(queues = NAME_USER_COUPON_WRITE_QUEUE)
    public void receiveUserCouponWriteMessage(UserCouponMsg userCouponMsg) {
        userCouponService.createUserCoupon(userCouponMsg.getUserCoupon());
    }
}
