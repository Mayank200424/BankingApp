package com.example.BankingApplication.Service;

import com.example.BankingApplication.Model.Bank;
import com.example.BankingApplication.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BankService {
    @Autowired
    BankRepository bankRepository;

    public void openAccount(Bank bank){
        double defaultBalance = 10000.0;
        bank.setBalance(defaultBalance + bank.getBalance());
        bank.setAccountNo(bank.generateAccountNumber());
        bankRepository.save(bank);
    }

    public void Withdraw(Long accountNo,double amount) throws Exception{
        Bank bank = bankRepository.findBankByAccountNo(accountNo);

        if(bank != null){
            if(bank.getBalance() < amount){
                throw new Exception("Insufficient balance");
            }else{
                bank.setBalance(bank.getBalance() - amount);
                bankRepository.save(bank);
            }
        }else {
            throw new Exception("Account not found");
        }
    }

    public void Deposit(Long accountNo,double amount) throws Exception{
        Bank bank = bankRepository.findBankByAccountNo(accountNo);

        if(bank != null){
            bank.setBalance(bank.getBalance() + amount);
            bankRepository.save(bank);
        }else{
            throw new Exception("Account Not found");
        }
    }

    public void transfer(Long FirstAccountNumber, Long SecondAccountNumber, double amount) throws Exception {
        Bank firstAccount = bankRepository.findBankByAccountNo(FirstAccountNumber);
        Bank secondAccount = bankRepository.findBankByAccountNo(SecondAccountNumber);

        if (firstAccount == null) {
            throw new Exception("First account not found.");
        }
        if (secondAccount == null) {
            throw new Exception("Second account not found.");
        }

        if (firstAccount.getBalance() < amount) {
            throw new Exception("Insufficient balance.");
        }

        firstAccount.setBalance(firstAccount.getBalance() - amount);
        secondAccount.setBalance(secondAccount.getBalance() + amount);

        List<Bank> list = new ArrayList<>();
        list.add(firstAccount);
        list.add(secondAccount);

        bankRepository.saveAll(list);
    }
    public Bank getBankByAcNumber(Long accountNo) {
        if (bankRepository.findBankByAccountNo(accountNo) != null) {
            return bankRepository.findBankByAccountNo(accountNo);
        }
        return null;
    }

}
