package com.grupo11.repositories;

import com.grupo11.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    //Investment findByAccount(Account cuenta); Falta cuenta
}
