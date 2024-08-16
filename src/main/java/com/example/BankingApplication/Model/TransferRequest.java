package com.example.BankingApplication.Model;

public class TransferRequest {
    private Long firstAccountNumber;
    private Long secondAccountNumber;
    private double amount;

    public Long getFirstAccountNumber() {
        return firstAccountNumber;
    }

    public void setFirstAccountNumber(Long firstAccountNumber) {
        this.firstAccountNumber = firstAccountNumber;
    }

    public Long getSecondAccountNumber() {
        return secondAccountNumber;
    }

    public void setSecondAccountNumber(Long secondAccountNumber) {
        this.secondAccountNumber = secondAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
