package com.example.BankingApplication.Repository;

import com.example.BankingApplication.Model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank,Integer> {
    Bank findBankByAccountNo(Long accountNo);
}
