package com.romanpolak.microloan.service

import com.romanpolak.microloan.MicroLoanApplication
import com.romanpolak.microloan.model.Loan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.PropertySource
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest(classes = MicroLoanApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PropertySource("application.properties")
class LoanServiceTest extends Specification {

    @Autowired
    LoanService loanService

    def "Create new loan"() {
        when:
        loanService.createLoan(100, 1500)

        then:
        Loan testLoan = loanService.getCurrentLoan()
        testLoan != null
        testLoan.amount == 1500
        testLoan.loanDate == LocalDate.now()
    }

    def "Incorrect amount range"() {
        when:
        loanService.createLoan(100, 100)

        then:
        loanService.getCurrentLoan() == null
    }

    def "Incorrect term range"() {
        when:
        loanService.createLoan(10, 1000)

        then:
        loanService.getCurrentLoan() == null
    }

    def "Extend term"() {
        when:
        loanService.createLoan(100, 1500)
        loanService.extendTerm()

        then:
        loanService.getCurrentLoan() != null
        loanService.getCurrentLoan().returnDate == LocalDate.now().plusDays(100).plusDays(90)
    }

}
