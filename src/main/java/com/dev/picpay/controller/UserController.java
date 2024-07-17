package com.dev.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.picpay.dto.MerchantDTO;
import com.dev.picpay.model.Merchant;
import com.dev.picpay.model.User;
import com.dev.picpay.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = userService.createUser(user);
        return ResponseEntity.ok(userCreated);
    }

    @PostMapping("/create-merchant")
    public ResponseEntity<Merchant> createMerchant(@RequestBody MerchantDTO merchantDTO) {
        Merchant merchantCreated = userService.createMerchant(merchantDTO);        
        return ResponseEntity.ok(merchantCreated);
    }
    
    
}
