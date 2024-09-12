package com.dreamquest.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@AllArgsConstructor@NoArgsConstructor
@ToString
@Getter
@Setter
public class Loans extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id", nullable = false)
    private Long loanId;

    @Column(name = "mobile_number", nullable = false, length = 15)
    private String mobileNumber;

    @Column(name = "loan_number", nullable = false, length = 100)
    private String loanNumber;

    @Column(name = "loan_type", nullable = false, length = 100)
    private String loanType;

    @Column(name = "total_loan", nullable = false)
    private int totalLoan;

    @Column(name = "amount_paid", nullable = false)
    private int amountPaid;

    @Column(name = "outstanding_amount", nullable = false)
    private int outstandingAmount;
}
