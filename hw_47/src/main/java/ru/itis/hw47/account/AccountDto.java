package ru.itis.hw47.account;

public class AccountDto {

    private final Long id;
    private final String name;
    private final String email;

    public AccountDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static AccountDto from(Account account) {
        return new AccountDto(account.getId(), account.getName(), account.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
