package ru.itis.hw46.account;

public class AccountDto {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;

    public AccountDto(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static AccountDto from(Account account) {
        return new AccountDto(
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
