package com.romanpolak.microcredit.controller;

import com.romanpolak.microcredit.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class CreditController {

    @Autowired
    LoanService loanService;

    @RequestMapping("/apply")
    public void applyForLoan(@RequestParam(name = "amount") double amount, @RequestParam(name = "term") int term){
        loanService.isLoanApplied(term, amount);
    }
    @RequestMapping("/extend")
    public void extendLoan(){

    }
}
