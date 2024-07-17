package com.dev.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.picpay.dto.TransferDTO;
import com.dev.picpay.model.Transfer;
import com.dev.picpay.service.ExternalAuthorizationService;
import com.dev.picpay.service.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private ExternalAuthorizationService externalAuthorizationService;

    @PostMapping("/create")
    public ResponseEntity<Transfer> postMethodName(@RequestBody TransferDTO transferDTO) {
        Transfer transferCreated = transferService.transfer(transferDTO.getPayer(), transferDTO.getPayee(), transferDTO.getValueTransfer());        
        return ResponseEntity.ok(transferCreated);
    }

    @GetMapping("/test-authorization")
    public Boolean testAuthorization() {
        return externalAuthorizationService.isAuthorized();
    }
    
    
    
}
