package me.sedov.TestWorkForPioneer.model;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Column(name = "date_of_birth")
        private LocalDate dateOfBirth;

        private String password;

        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
        private Account account;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<EmailData> emailDataList;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<PhoneData> phoneDataList;
}
