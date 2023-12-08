package com.grupo11.services;

import com.grupo11.entities.Account;
import com.grupo11.entities.User;
import com.grupo11.entities.dtos.AccountDto;
import com.grupo11.entities.enums.AccountType;
import com.grupo11.mappers.AccountMapper;
import com.grupo11.mappers.UserMapper;
import com.grupo11.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }


    public List<AccountDto> getAccounts() {
        return repository.findAll().stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    public AccountDto getAccountById(Long id) {
        Account acc = repository.findById(id).get();
        return AccountMapper.accountToDto(acc);
    }

    public AccountDto createAccount(AccountDto dto) {
       // dto.setType(AccountType.CAJA_AHORRO_PESOS);
        if (dto.getAmount() == null) {
            dto.setAmount(BigDecimal.ZERO);
        }
        dto.setCreated_at(LocalDateTime.now());
        Account newAccount = AccountMapper.dtoToAccount(dto);

        return AccountMapper.accountToDto(repository.save(newAccount));
    }

    public AccountDto updateAccount(Long id, AccountDto dto) {
        if (repository.existsById(id)) {
            Account acc = repository.findById(id).get();
            if (dto.getAlias() != null) {
                acc.setAlias(dto.getAlias());
            }

            if (dto.getType() != null) {
                acc.setType(dto.getType());
            }

            if (dto.getCbu() != null) {
                acc.setCbu(dto.getCbu());
            }
            if (dto.getAmount() != null) {
                acc.setAmount(dto.getAmount());
            }

            acc.setUpdated_at(LocalDateTime.now());

            Account accountModified = repository.save(acc);
            return AccountMapper.accountToDto(accountModified);

        } else {
            return null;
        }
    }

    public String deleteAccount(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "La cuenta con id: " + id + " ha sido eliminada";
        } else {
            return "No se pudo eliminar la cuenta";
        }
    }
}
