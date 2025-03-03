package com.example.coupon_system.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class UserCoupon implements Serializable {
    private int id;
    private int userId;
    private int couponId;
    private int status;
    private Date usedAt;
    private Date claimedAt;

    public static class UserCouponBuilder {
        private int id;
        private int userId;
        private int couponId;
        private int status;
        private Date usedAt;
        private Date claimedAt;

        public UserCouponBuilder() {
        }

        public UserCouponBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserCouponBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserCouponBuilder couponId(int couponId) {
            this.couponId = couponId;
            return this;
        }

        public UserCouponBuilder status(int status) {
            this.status = status;
            return this;
        }

        public UserCouponBuilder usedAt(Date usedAt) {
            this.usedAt = usedAt;
            return this;
        }

        public UserCouponBuilder claimedAt(Date claimedAt) {
            this.claimedAt = claimedAt;
            return this;
        }

        public UserCoupon build() {
            return new UserCoupon(id, userId, couponId, status, usedAt, claimedAt);
        }
    }
}
