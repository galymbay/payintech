package kz.galymbay.payintech.controller;

import kz.galymbay.payintech.entity.Client;
import kz.galymbay.payintech.entity.Loan;
import kz.galymbay.payintech.entity.MyUserPrincipal;
import kz.galymbay.payintech.entity.RemainingLoan;
import kz.galymbay.payintech.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/loan")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> addLoan(@AuthenticationPrincipal MyUserPrincipal userPrincipal, @RequestBody Loan loan) {
        Client client = userPrincipal.getClient();
        return ResponseEntity.ok().body(loanService.createLoan(loan, client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemainingLoan> getLoan(@AuthenticationPrincipal MyUserPrincipal userPrincipal, @PathVariable Long id) {
        Client client = userPrincipal.getClient();
        return ResponseEntity.ok().body(loanService.getRemainingLoan(id, client));
    }
}
