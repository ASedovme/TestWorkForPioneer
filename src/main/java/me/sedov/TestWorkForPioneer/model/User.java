package me.sedov.TestWorkForPioneer.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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

        @Column(nullable = false)
        private String password;

        @JsonManagedReference
        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
        private Account account;

        @JsonManagedReference
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<EmailData> emailDataList;

        @JsonManagedReference
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<PhoneData> phoneDataList;
}
