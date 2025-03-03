package com.example.coupon_system.persistence.entity;

import com.example.coupon_system.utils.enums.UserCouponStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCoupon implements Serializable {
    private int id;
    private int userId;
    private int couponId;
    private int status;
    private String statusName;
    private Date usedAt;
    private Date claimedAt;
    private Coupon coupon;
    private User user;


    public void setStatus(int status) {
        this.status = status;
        this.statusName = UserCouponStatus.getUserCouponStatusByCode(status);
    }

    public void setStatusName(String statusName) {
        this.statusName = UserCouponStatus.getUserCouponStatusByCode(this.status);
    }


    public static class UserCouponBuilder {
        private int id;
        private int userId;
        private int couponId;
        private int status;
        private Date usedAt;
        private Date claimedAt;
        private Coupon coupon;
        private User user;
        private String statusName;



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
            this.statusName = UserCouponStatus.getUserCouponStatusByCode(status);
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

        public UserCouponBuilder coupon(Coupon coupon) {
            this.coupon = coupon;
            return this;
        }

        public UserCouponBuilder user(User user) {
            this.user = user;
            return this;
        }

        public UserCouponBuilder statusName(String statusName) {
            this.statusName = statusName;
            return this;
        }

        public UserCoupon build() {
            statusName = UserCouponStatus.getUserCouponStatusByCode(status);
            return new UserCoupon(id, userId, couponId, status, statusName, usedAt, claimedAt, coupon, user);
        }
    }
}
