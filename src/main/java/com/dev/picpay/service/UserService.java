package com.dev.picpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.picpay.dto.MerchantDTO;
import com.dev.picpay.model.Merchant;
import com.dev.picpay.model.User;
import com.dev.picpay.repository.MerchantRepository;
import com.dev.picpay.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Transactional
    public User createUser(final User user) {
        user.setCreateAt(LocalDateTime.now());
        return userRepository.save(user);
    }
    
    @Transactional
    public Merchant createMerchant(final MerchantDTO merchantDTO ) {

        User user = new User();
        user.setFullName(merchantDTO.getFullName());
        user.setCpf(merchantDTO.getCpf());
        user.setEmail(merchantDTO.getEmail());
        user.setPassword(merchantDTO.getPassword());
        user.setBalance(BigDecimal.ZERO);
        user.setCreateAt(LocalDateTime.now());
        user = userRepository.save(user);

        Merchant merchant = new Merchant();
        merchant.setMerchant(user);
        merchant.setCnpj(merchantDTO.getCnpj());
        return merchantRepository.save(merchant);
    }


}
