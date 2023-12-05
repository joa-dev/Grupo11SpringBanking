package com.grupo11.services;

import com.grupo11.entities.Account;
import com.grupo11.entities.Investment;
import com.grupo11.entities.User;
import com.grupo11.entities.dtos.InvestmentDto;
import com.grupo11.entities.dtos.UserDto;
import com.grupo11.exceptions.AccountNotFoundException;
import com.grupo11.exceptions.InsuficientFoundsException;
import com.grupo11.mappers.InvestmentMapper;
import com.grupo11.mappers.UserMapper;
import com.grupo11.repositories.AccountRepository;
import com.grupo11.repositories.InvestmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class InvestmentService {

    private InvestmentRepository investmentRepository;
    private AccountRepository accountRepository;

    public InvestmentService (InvestmentRepository investmentRepository, AccountRepository accountsRepository) {
        this.investmentRepository = investmentRepository;
        this.accountRepository = accountsRepository;
    }

    public List<Investment> getAllInvestments(){
        List<Investment> investment = investmentRepository.findAll();
        return investment;
    }

    @Transactional
    public InvestmentDto performInvestment(InvestmentDto dto){
        //comprobar que la cuenta existe
        Account originAccount = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Cuenta no encontada con Id: " + dto.getAccountId()));

        //comprobar que la cuenta tenga fondos suficientes
        if (originAccount.getAmount().compareTo(dto.getAmount()) < 0) {
            throw new InsuficientFoundsException("Fondos insuficientes en la cuenta con Id: " + dto.getId());
        }

        //extraer dinero de la cuenta
        originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));
        // Guardar cambios en la cuenta
        accountRepository.save(originAccount);

        //Crear Inversion
        Investment inversion = new Investment();
        inversion.setAmount(dto.getAmount());
        inversion.setAccount(originAccount);

        LocalDate fechaInicio = LocalDate.now();
        inversion.setStartDate(fechaInicio);
        inversion.setEndDate(fechaInicio.plusDays(30));

        //realizar calculo
        BigDecimal interes =BigDecimal.valueOf(inversion.getInvestmentInterest()/100);
        BigDecimal totalInversion = dto.getAmount().multiply(interes).add(dto.getAmount());
        inversion.setBalance(totalInversion);

        //guardar inversion
        investmentRepository.save(inversion);

        //devolver DTO inversion
        return InvestmentMapper.investmentToDto(inversion);
    }

}
