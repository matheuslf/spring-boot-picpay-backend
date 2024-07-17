package com.dev.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.picpay.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    
}
