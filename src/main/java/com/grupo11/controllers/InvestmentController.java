package com.grupo11.controllers;

import com.grupo11.entities.Investment;
import com.grupo11.entities.User;
import com.grupo11.services.InvestmentService;
import com.grupo11.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService service;

// Obtener una lista de inversiones realizadas

    @GetMapping
    public ResponseEntity<List<Investment>> getAllInvestment(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllInvestments());
    }

}
