package com.example.BankingApplication.Model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_no")
    private Long accountNo;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long generateAccountNumber() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String accNo = uuid.substring(0,10);
        return Long.parseLong(accNo,16) % 10000000000L;
    }

    public void BankAccount() {
        this.accountNo = generateAccountNumber();
    }
}
