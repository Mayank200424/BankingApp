package com.example.BankingApplication.Controller;

import com.example.BankingApplication.Model.Bank;
import com.example.BankingApplication.Model.TransferRequest;
import com.example.BankingApplication.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank")
public class BankController {
    @Autowired
    BankService bankService;

    @PostMapping("openAccount")
    public void openAc(@RequestBody Bank bank){
        bankService.openAccount(bank);
    }

    @PostMapping("withdraw")
    public ResponseEntity<String> withdraw(@RequestBody Bank bank) {
        try {
            Long accountNo = bank.getAccountNo();
            double amount = bank.getBalance();
            bankService.Withdraw(accountNo,amount);
            return ResponseEntity.ok("Withdraw successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("deposit")
    public ResponseEntity<String> deposit(@RequestBody Bank bank){
        try{
            Long accountNo = bank.getAccountNo();
            double amount = bank.getBalance();
            bankService.Deposit(accountNo,amount);
            return ResponseEntity.ok("Deposit successful");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest) {
        try {
            bankService.transfer(
                    transferRequest.getFirstAccountNumber(),
                    transferRequest.getSecondAccountNumber(),
                    transferRequest.getAmount()
            );
            return ResponseEntity.ok("Transaction successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("getDetails")
    public ResponseEntity<Bank> getDetails(@RequestParam Long accountNo) {
        Bank bank = bankService.getBankByAcNumber(accountNo);
        if (bank != null) {
            return ResponseEntity.ok(bank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
