package com.dreamquest.cards.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@AllArgsConstructor@NoArgsConstructor
@ToString
@Getter
@Setter
public class Cards extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardID;
    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
}
