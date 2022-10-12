package kz.galymbay.payintech.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name = "loan")
@NoArgsConstructor
public class Loan {
    @Id
    @Column(name = "loan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_sum")
    private int loanSum;

    private int period;

    private int interestRate;

    @JsonIgnore
    private double monthlyPay;

    @JsonIgnore
    private LocalDate startDate;

    @JsonIgnore
    private LocalDate endDate;

    public Loan(int loanSum, int period, int interestRate) {
        this.loanSum = loanSum;
        this.period = period;
        this.interestRate = interestRate;
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusMonths(period);
        this.monthlyPay = (loanSum * interestRate) / period;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
