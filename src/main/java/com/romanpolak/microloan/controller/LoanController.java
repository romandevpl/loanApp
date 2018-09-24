package com.romanpolak.microloan.controller;

import com.romanpolak.microloan.model.Loan;
import com.romanpolak.microloan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ResponseEntity applyForLoan(@RequestParam(name = "amount") double amount, @RequestParam(name = "term") int term) {
        loanService.createLoan(term, amount);
        Loan loan = loanService.getCurrentLoan();
        if (loan != null) {
            return ResponseEntity.ok().body("ACCEPTED " + loan);
        } else {
            return ResponseEntity.ok().body("REJECTED");
        }
    }

    @RequestMapping(value = "/extend", method = RequestMethod.GET)
    public ResponseEntity extendLoan() {
        loanService.extendTerm();
        Loan loan = loanService.getCurrentLoan();
        if (loan != null) {
            return ResponseEntity.ok().body("LOAN EXTENDED " + loan);
        } else {
            return ResponseEntity.ok().body("REJECTED");
        }
    }
}
