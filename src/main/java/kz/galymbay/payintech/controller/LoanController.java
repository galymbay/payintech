package kz.galymbay.payintech.controller;

import kz.galymbay.payintech.entity.Loan;
import kz.galymbay.payintech.entity.RemainingLoan;
import kz.galymbay.payintech.entity.UserPrincipal;
import kz.galymbay.payintech.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/loan")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> addLoan(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Loan loan) {
        return ResponseEntity.ok().body(loanService.createLoan(loan, userPrincipal.getClient()));
    }

    @GetMapping
    public ResponseEntity<List<RemainingLoan>> getLoan(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok().body(loanService.getRemainingLoan(userPrincipal.getClient()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RemainingLoan>> getClientLoan(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {
        return ResponseEntity.ok().body(loanService.getRemainingLoan(id));
    }


}
