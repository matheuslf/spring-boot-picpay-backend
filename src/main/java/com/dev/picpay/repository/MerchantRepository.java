package com.dev.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.picpay.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    
}
