package com.romanpolak.microcredit.service;

import com.romanpolak.microcredit.model.Loan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.lang.Math.toIntExact;

@Service
public class LoanService {

    @Value("${amount.min}")
    private double minAmount;
    @Value("${amount.max}")
    private double maxAmount;

    @Value("${term.min}")
    private int minTerm;
    @Value("${term.max}")
    private int maxTerm;

    @Value("${extension.term}")
    private int extensionTerm;

    private final static int INTEREST_RATE = 10;

    private Loan currentLoan;

    public Loan createLoan(int term, double amount){
        if (isLoanApplicable(term, amount)){
            Loan loan = new Loan();
            loan.setAmount(amount);
            loan.setLoanDate(LocalDate.now());
            loan.setReturnDate(calculateReturnDate(term));
            loan.setPayback(calculatePayback(amount, term));

            currentLoan = loan;
            return loan;
        }else {
            return null;
        }
    }

    public Loan extendTerm(){
        currentLoan.setReturnDate(currentLoan.getReturnDate().plusDays(extensionTerm));
        currentLoan.setPayback(calculatePayback(currentLoan.getAmount(),
                toIntExact(DAYS.between(currentLoan.getLoanDate(), currentLoan.getReturnDate()))));
        return currentLoan;
    }

    public boolean isLoanApplicable(int term, double amount) {
       return inDefinedRange(term, amount) && !appliedAtNightForMaxAmount(amount);
    }

    private boolean inDefinedRange(int term, double amount) {
        return term <= maxTerm && term >= minTerm && amount <= maxAmount && amount >= minAmount;
    }

    private boolean appliedAtNightForMaxAmount(double amount){
        return LocalTime.now().isBefore(LocalTime.MIDNIGHT) && LocalTime.now().isAfter(LocalTime.parse("06:00"))
                && amount == maxAmount;
    }

    private LocalDate calculateReturnDate(int range){
        return LocalDate.now().plusDays(range);
    }

    private double calculatePayback(double amount, int term){
        return amount * (1 + (INTEREST_RATE / 100.0) * (term / 365.0));
    }
}
