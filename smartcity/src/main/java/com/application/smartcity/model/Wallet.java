package com.application.smartcity.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer walletId;
    private Double balance;
    private Double blockedAmount;

    @OneToOne
    @MapsId // Map walletId to userId
    @JoinColumn(name = "wallet_id")
    private User user;
}


