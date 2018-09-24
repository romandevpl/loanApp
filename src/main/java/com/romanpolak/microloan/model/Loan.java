package com.romanpolak.microloan.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class Loan {

    private double amount;

    private LocalDate loanDate;

    private LocalDate returnDate;

    private double payback;

    @Override
    public String toString() {
        return "Loan{" +
                "amount=" + amount +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", payback=" + String.format("%.2f", payback) +
                '}';
    }
}
