package kz.galymbay.payintech.service;

import kz.galymbay.payintech.entity.Client;
import kz.galymbay.payintech.entity.Loan;
import kz.galymbay.payintech.entity.RemainingLoan;
import kz.galymbay.payintech.exception.ServiceFaultException;
import kz.galymbay.payintech.repository.ClientRepository;
import kz.galymbay.payintech.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, ClientRepository clientRepository) {
        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
    }

    public Optional<Loan> getLoan(Long id) {
        return loanRepository.findById(id);
    }

    public Loan createLoan(Loan loan, Client client) {
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(loan.getStartDate().plusMonths(loan.getPeriod()));
        loan.setMonthlyPay((loan.getLoanSum() * ((loan.getInterestRate() + 100) / 100)) / loan.getPeriod());

        loan.setClient(client);
        return loanRepository.save(loan);
    }

    public List<RemainingLoan> getRemainingLoan(Client client) {
        List<Loan> all = loanRepository.findAllByClient(client);
        List<RemainingLoan> remainingLoans = new ArrayList<>();
        for (Loan currLoan : all) {
            RemainingLoan remainingLoan = new RemainingLoan(currLoan);
            remainingLoans.add(remainingLoan);
        }

        return remainingLoans;
    }

    public List<RemainingLoan> getRemainingLoan(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent())
            throw new ServiceFaultException("Client with this id wasn't found", HttpStatus.BAD_REQUEST);
        List<Loan> all = loanRepository.findAllByClient(client.get());
        List<RemainingLoan> remainingLoans = new ArrayList<>();
        for (Loan currLoan : all) {
            RemainingLoan remainingLoan = new RemainingLoan(currLoan);
            remainingLoans.add(remainingLoan);
        }

        return remainingLoans;
    }
}
