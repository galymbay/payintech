package kz.galymbay.payintech.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @Column(name = "loan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_sum")
    private int loanSum;

    @Column(name = "period")
    private int period;

    @Column(name = "interest_rate")
    private int interestRate;

    @JsonIgnore
    @Column(name = "monthly_pay")
    private double monthlyPay;

    @JsonIgnore
    @Column(name = "start_date")
    private LocalDate startDate;

    @JsonIgnore
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
