package com.grupo11.controllers;

import com.grupo11.entities.Investment;
import com.grupo11.entities.User;
import com.grupo11.entities.dtos.InvestmentDto;
import com.grupo11.entities.dtos.UserDto;
import com.grupo11.services.InvestmentService;
import com.grupo11.services.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<InvestmentDto>> getAllInvestment(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllInvestments());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<InvestmentDto> getInvestmentById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getInvestmentById(id));
    }


    @PostMapping
    public ResponseEntity<InvestmentDto> performInvestment(@RequestBody @Valid InvestmentDto inversion){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.performInvestment(inversion));
    }

}
