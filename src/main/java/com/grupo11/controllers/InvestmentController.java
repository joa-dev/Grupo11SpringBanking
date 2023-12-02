package com.grupo11.controllers;

import com.grupo11.entities.Investment;
import com.grupo11.entities.User;
import com.grupo11.entities.dtos.InvestmentDto;
import com.grupo11.services.InvestmentService;
import com.grupo11.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService service;

// Obtener una lista de inversiones realizadas

    @GetMapping
    public ResponseEntity<List<Investment>> getInvestment(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getInvestments());
    }

    @PostMapping()
    public ResponseEntity<InvestmentDto> performInvestment(@RequestBody InvestmentDto inversion){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.performInvestment(inversion));
    }

}
