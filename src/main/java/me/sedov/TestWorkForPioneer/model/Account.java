package me.sedov.TestWorkForPioneer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private BigDecimal balance;

    public BigDecimal getInitialDeposit() {
        return balance;
    }
}
