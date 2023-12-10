package com.grupo11.services;

import com.grupo11.entities.Account;
import com.grupo11.entities.Investment;
import com.grupo11.entities.User;
import com.grupo11.entities.dtos.AccountDto;
import com.grupo11.entities.dtos.InvestmentDto;
import com.grupo11.entities.dtos.UserDto;
import com.grupo11.exceptions.AccountNotFoundException;
import com.grupo11.exceptions.InsuficientFoundsException;
import com.grupo11.exceptions.InvestmentNotFoundException;
import com.grupo11.exceptions.NegativeInvestmentException;
import com.grupo11.mappers.AccountMapper;
import com.grupo11.mappers.InvestmentMapper;
import com.grupo11.mappers.UserMapper;
import com.grupo11.repositories.AccountRepository;
import com.grupo11.repositories.InvestmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentService {

    private InvestmentRepository investmentRepository;
    private AccountRepository accountRepository;
    LocalDate fechaInicio=null;

    public InvestmentService (InvestmentRepository investmentRepository, AccountRepository accountsRepository) {
        this.investmentRepository = investmentRepository;
        this.accountRepository = accountsRepository;
    }

    public List<InvestmentDto> getAllInvestments() {
       List<InvestmentDto>investmentDtoList = investmentRepository.findAll().stream()
                .map(InvestmentMapper::investmentToDto)
                .collect(Collectors.toList());
       if(investmentDtoList.isEmpty()){
        throw new InvestmentNotFoundException("No se encontraron inversiones.");
       }
           return investmentDtoList;
    }
    public InvestmentDto getInvestmentById(Long id) {
        if(!investmentRepository.existsById(id)){
            throw new InvestmentNotFoundException("No se encontró la inversión con ID: "+id);
        }
        Investment investment = investmentRepository.findById(id).get();
        return InvestmentMapper.investmentToDto(investment);
    }

    @Transactional
    public InvestmentDto performInvestment(InvestmentDto dto){

        //comprobar que la cuenta existe
        Account originAccount = existAccount(dto);

        //comprobar que la cuenta tenga fondos suficientes
         hasAccountFounds(originAccount,dto);

        //extraer dinero de la cuenta
        originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));

        // Guardar cambios en la cuenta
        accountRepository.save(originAccount);

        //Crear Inversion
        Investment inversion = createInvestment(originAccount,dto);

        //Setear interes segun el req.Body
        setInvestmentInterest(inversion);

        //Calcula las ganancias y las seteaen la cuenta
        setInterestEarned(inversion,dto);

        //guardar inversion
        investmentRepository.save(inversion);

        //devolver DTO inversion
        return InvestmentMapper.investmentToDto(inversion);
    }

    //------METODOS---------

    //Chequeo si la cuenta existe
        private Account existAccount(InvestmentDto dto){
            Account originAccount = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new AccountNotFoundException("Cuenta no encontada con Id: " + dto.getAccountId()));
            return originAccount;
        }

        //Chequeoo si tiene fondos
        private void hasAccountFounds(Account originAccount, InvestmentDto dto ){
            if (originAccount.getAmount().compareTo(dto.getAmount()) < 0) {
                throw new InsuficientFoundsException("Fondos insuficientes en la cuenta con Id: " + dto.getAccountId());
            }
        }

        //Crea la inversion
        private Investment createInvestment(Account originAccount,InvestmentDto dto){
            Investment inversion = new Investment();
            BigDecimal amount = dto.getAmount();
            if(amount.intValue()<=0){
                throw new NegativeInvestmentException("La inversión debe ser mayor a 0,00 (CERO). ");
            }
            inversion.setAmount(dto.getAmount());
            inversion.setAccount(originAccount);

            //De no venir el atributo investmentPeriod
            //setea el atributo a 30
            if(dto.getInvestmentPeriod()==null){
                inversion.setInvestmentPeriod(30);
            }else{
                inversion.setInvestmentPeriod(dto.getInvestmentPeriod());
            }
            fechaInicio = LocalDate.now();
            inversion.setStartDate(fechaInicio);

            return inversion;
        }

        // Setea el interes según cantidad de dias a invertir
        private void setInvestmentInterest(Investment inversion){
            int periodoInversion = inversion.getInvestmentPeriod();

            if( (periodoInversion >=0) & (periodoInversion<=30)){
                inversion.setInvestmentInterest(10);
                inversion.setEndDate(fechaInicio.plusDays(30));
            }
            if((periodoInversion >30) & (periodoInversion<=60)){
                inversion.setInvestmentInterest(20);
                inversion.setEndDate(fechaInicio.plusDays(60));
            }
            if((periodoInversion >60) & (periodoInversion<=90)){
                inversion.setInvestmentInterest(30);
                inversion.setEndDate(fechaInicio.plusDays(90));
            }
            if(periodoInversion>90){
                inversion.setInvestmentInterest(50);
                inversion.setEndDate(fechaInicio.plusDays(100));
            }
            if(periodoInversion<0){
                throw new NegativeInvestmentException("El período de tiempo no puede ser negativo.");
            }
        }

        //Calcula el interes ganado segun los dias
    private void setInterestEarned(Investment inversion, InvestmentDto dto){
        BigDecimal interes =BigDecimal.valueOf(inversion.getInvestmentInterest()/100);
        BigDecimal totalInversion = dto.getAmount().multiply(interes).add(dto.getAmount());
        inversion.setBalance(totalInversion);
    }
}
