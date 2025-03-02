package com.example.crm.service;

import com.example.crm.dto.SmsRequestDto;

public interface ISendSmsService {

    String sendSms(SmsRequestDto smsRequestDto);
}
