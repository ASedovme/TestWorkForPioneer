package me.sedov.TestWorkForPioneer.dto;

import lombok.Data;
import me.sedov.TestWorkForPioneer.model.Account;
import me.sedov.TestWorkForPioneer.model.EmailData;
import me.sedov.TestWorkForPioneer.model.PhoneData;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private Account account;
    private List<EmailData> emailDataList;
    private List<PhoneData> phoneDataList;

    public UserDTO(Long id, String name, LocalDate dateOfBirth, Account account, List<EmailData> emailDataList, List<PhoneData> phoneDataList) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
        this.emailDataList = emailDataList;
        this.phoneDataList = phoneDataList;
    }
}
