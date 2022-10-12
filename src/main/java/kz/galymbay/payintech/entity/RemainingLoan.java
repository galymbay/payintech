package kz.galymbay.payintech.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Getter
@NoArgsConstructor
public class RemainingLoan {
    private double remains;
    private long remainingPaymentsCount;
    private double nextPaymentAmount;
    private LocalDate nextPaymentDate;
    private int earlyPayment;

    public RemainingLoan(Loan currentLoan) {
        LocalDate localDate = LocalDate.now();
        long remainingMonthCount = Period.between(localDate, currentLoan.getEndDate()).toTotalMonths();
        this.remains = remainingMonthCount * currentLoan.getMonthlyPay();
        this.remainingPaymentsCount = remainingMonthCount;
        this.nextPaymentAmount = currentLoan.getMonthlyPay();
        // TODO: fix nextPaymentDate
        this.nextPaymentDate = localDate;
        this.earlyPayment = 120000;
    }
}
