package com.dev.picpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.picpay.model.Transfer;
import com.dev.picpay.model.User;
import com.dev.picpay.repository.MerchantRepository;
import com.dev.picpay.repository.TransferRepository;
import com.dev.picpay.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private ExternalAuthorizationService externalAuthorizationService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public Transfer transfer(Long payerId, Long payeeId, BigDecimal valueTransfer) {

        merchantRepository.findById(payerId).orElseThrow(() -> new RuntimeException("Merchant cannot make transactions"));
        User uPayer = userRepository.findById(payerId).orElseThrow(() -> new RuntimeException("User not found. Try again!"));
        User uPayee = userRepository.findById(payeeId).orElseThrow(() -> new RuntimeException("Destination not found. Try again!"));

        if (uPayer.getBalance().compareTo(valueTransfer) < 0) {
            throw new RuntimeException("Insufficient balance!");
        }

        if (!externalAuthorizationService.isAuthorized()) {
            throw new RuntimeException("Authorization failed!");
        }

        uPayer.setBalance(uPayer.getBalance().subtract(valueTransfer));
        uPayee.setBalance(uPayee.getBalance().add(valueTransfer));

        Transfer transfer = new Transfer();
        transfer.setPayer(uPayer);
        transfer.setPayee(uPayee);
        transfer.setTransferAt(LocalDateTime.now());
        transfer.setValueTransfer(valueTransfer);

        userRepository.save(uPayer);
        userRepository.save(uPayee);
        transferRepository.save(transfer);

        notificationService.notify(uPayer, "You sended a transfer of: "+valueTransfer);
        notificationService.notify(uPayee, "You received a transfer of: "+valueTransfer);

        return transfer;

    }
    
}
