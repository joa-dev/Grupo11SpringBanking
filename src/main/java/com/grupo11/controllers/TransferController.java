package com.grupo11.controllers;

import com.grupo11.entities.dtos.TransferDto;
import com.grupo11.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService service;

    public TransferController(TransferService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TransferDto>> getTransfers(){
        List<TransferDto> transfers = service.getTransfers();
        return ResponseEntity.status(HttpStatus.OK).body(transfers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDto> getTransferById(@PathVariable Long id){
        TransferDto transfer = service.getTransferById(id);
        return ResponseEntity.status(HttpStatus.OK).body(transfer);
    }


    @PostMapping
    public ResponseEntity<TransferDto> performTransfer(@RequestBody @Valid TransferDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.performTransfer(dto));
    }
}