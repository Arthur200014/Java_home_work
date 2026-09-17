package ru.itis.hw45.account.dto;

import ru.itis.hw45.account.Account;

public class AccountResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public AccountResponse() {
    }

    public AccountResponse(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getFirstName(),
                account.getLastName(),
                account.getEmail()
        );
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
