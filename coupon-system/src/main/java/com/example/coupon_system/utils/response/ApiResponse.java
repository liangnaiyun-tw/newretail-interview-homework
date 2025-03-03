package com.example.coupon_system.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private HttpStatus status;

    public static class ApiResponseBuilder<T> {
        private T data;
        private String message;
        private HttpStatus status;

        public ApiResponseBuilder() {
        }

        public ApiResponseBuilder data(T data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(data, message, status);
        }
    }



}
