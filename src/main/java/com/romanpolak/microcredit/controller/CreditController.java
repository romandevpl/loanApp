package com.romanpolak.microcredit.controller;

import com.romanpolak.microcredit.model.Loan;
import com.romanpolak.microcredit.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class CreditController {

    @Autowired
    LoanService loanService;

    @RequestMapping("/apply")
    public ResponseEntity applyForLoan(@RequestParam(name = "amount") double amount, @RequestParam(name = "term") int term) {
        if(loanService.isLoanApplicable(term, amount)){
            Loan result = loanService.createLoan(term, amount);
            return ResponseEntity.ok().body("ACCEPTED " + result);
        }else{
            return ResponseEntity.ok().body("REJECTED");
        }
    }

    @RequestMapping("/extend")
    public ResponseEntity extendLoan() {
        Loan result = loanService.extendTerm();
        return ResponseEntity.ok().body("LOAN EXTENDED " + result);
    }
}
