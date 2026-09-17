package ru.itis.hw45.account;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.hw45.account.dto.AccountResponse;
import ru.itis.hw45.account.dto.NewAccountRequest;

import java.util.List;

@RestController
public class AccountController implements AccountApi {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public List<AccountResponse> getAccounts() {
        return accountService.findAll();
    }

    @Override
    public AccountResponse addAccount(NewAccountRequest request) {
        return accountService.create(request);
    }
}
