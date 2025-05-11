package me.sedov.TestWorkForPioneer.model;

import lombok.Data;

import jakarta.persistence.*;
import javax.validation.constraints.Email;

@Data
@Entity
@Table(name = "email_data")
public class EmailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Email // Валидация email-адреса
    private String email;
}
