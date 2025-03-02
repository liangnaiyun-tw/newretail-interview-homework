package com.example.crm.utils.rabbitmq;

import com.example.crm.persistence.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class NotificationMsg<T> implements Serializable {
    private Message message;
    private String routingKey;
    private T data;




    public static class NotificationMsgBuilder<T> {
        private Message message;
        private String routingKey;
        private T data;

        public NotificationMsgBuilder message(Message message) {
            this.message = message;
            return this;
        }

        public NotificationMsgBuilder routingKey(String routingKey) {
            this.routingKey = routingKey;
            return this;
        }

        public NotificationMsgBuilder data(T data) {
            this.data = data;
            return this;
        }

        public NotificationMsg build() {
            return new NotificationMsg(message, routingKey, data);
        }
    }
}
