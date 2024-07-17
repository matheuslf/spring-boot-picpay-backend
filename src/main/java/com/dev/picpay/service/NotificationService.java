package com.dev.picpay.service;

import org.springframework.stereotype.Service;

import com.dev.picpay.model.User;

@Service
public class NotificationService {

    public boolean notify(User user, String mensagem) {
        return true;
    }
    
}
