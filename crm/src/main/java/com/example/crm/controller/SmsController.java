package com.example.crm.controller;

import com.example.crm.dto.SmsRequestDto;
import com.example.crm.service.ISendSmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/sms")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsController {

    private final ISendSmsService sendSmsService;
    @PostMapping("/send")
    public String sendSms(@RequestBody SmsRequestDto smsRequestDto) {

        try {
            return sendSmsService.sendSms(smsRequestDto);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to send SMS: " + e.getMessage());
        }



    }
}
