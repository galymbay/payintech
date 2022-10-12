package kz.galymbay.payintech.service;

import kz.galymbay.payintech.entity.Client;
import kz.galymbay.payintech.entity.Loan;
import kz.galymbay.payintech.entity.RemainingLoan;
import kz.galymbay.payintech.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Optional<Loan> getLoan(Long id) {
        return loanRepository.findById(id);
    }

    public Loan createLoan(Loan loan, Client client) {
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(loan.getStartDate().plusMonths(loan.getPeriod()));
        loan.setMonthlyPay((loan.getLoanSum() * loan.getInterestRate()) / loan.getPeriod());

        loan.setClient(client);
        return loanRepository.save(loan);
    }

    public RemainingLoan getRemainingLoan(Long id, Client client) {
//        Optional<Loan> loan = getLoan(id);
//        if (loan.isEmpty())
//            throw new UsernameNotFoundException("Loan not found");

        Loan currentLoan = getLoan(id).get();
        RemainingLoan remainingLoan = new RemainingLoan(currentLoan);

        return remainingLoan;
    }
}
