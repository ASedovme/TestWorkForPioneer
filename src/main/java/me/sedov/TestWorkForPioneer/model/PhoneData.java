package me.sedov.TestWorkForPioneer.model;


import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "PHONE_DATA")
public class PhoneData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String phone;
}
