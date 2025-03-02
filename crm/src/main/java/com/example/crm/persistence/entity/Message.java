package com.example.crm.persistence.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Message implements Serializable {
    private int id;
    private int customerId;
    private String message;
    private int status;
    private Date createdAt;
    private Date sentAt;

    public Message(int id, int customerId, String message, int status, Date createdAt, Date sentAt) {
        this.id = id;
        this.customerId = customerId;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.sentAt = sentAt;
    }

    public static class MessageBuilder {
        private int id;
        private int customerId;
        private String message;
        private int status;
        private Date createdAt;
        private Date sentAt;

        public MessageBuilder() {
        }

        public MessageBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MessageBuilder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public MessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MessageBuilder status(int status) {
            this.status = status;
            return this;
        }

        public MessageBuilder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MessageBuilder sentAt(Date sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public Message build() {
            return new Message(id, customerId, message, status, createdAt, sentAt);
        }
    }

}
