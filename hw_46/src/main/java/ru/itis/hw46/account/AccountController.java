package ru.itis.hw46.account;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.hw46.common.PageResponse;

@RestController
public class AccountController implements AccountApi {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public PageResponse<AccountDto> getAccounts(int page, int size, String sort) {
        return accountService.findAll(page, size, sort);
    }

    @Override
    public AccountDto addAccount(NewAccountDto account) {
        return accountService.create(account);
    }

    @Override
    public AccountDto getAccount(Long id) {
        return accountService.findById(id);
    }
}
