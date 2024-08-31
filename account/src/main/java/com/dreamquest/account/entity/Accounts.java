package com.dreamquest.account.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Accounts extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "customer_id")
    private Long customerID;

}
