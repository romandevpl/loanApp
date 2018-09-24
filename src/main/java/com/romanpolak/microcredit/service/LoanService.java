package com.romanpolak.microcredit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Value("{amount.min}")
    private double minAmount;
    @Value("{amount.max")
    private double maxAmount;

    @Value("{term.min}")
    private int minTerm;
    @Value("{term.max}")
    private int maxTerm;

    @Value("{extension.term}")
    private int extensionTerm;

    public boolean isLoanApplied(int term, double amount){
        if(term <= maxTerm && term >= minTerm && amount <= maxAmount && amount >= minAmount){
            return true;
        }else {
            return false;
        }
    }
}
