package com.grupo11.services;

import com.grupo11.entities.Investment;
import com.grupo11.entities.User;
import com.grupo11.entities.dtos.UserDto;
import com.grupo11.mappers.UserMapper;
import com.grupo11.repositories.InvestmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    private InvestmentRepository repository;

    public InvestmentService (InvestmentRepository repository) {
        this.repository = repository;
    }

    public List<Investment> getAllInvestments(){
        List<Investment> investment = repository.findAll();
        return investment;
    }



}
