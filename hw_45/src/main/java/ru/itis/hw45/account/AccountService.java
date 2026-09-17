package ru.itis.hw45.account;

import org.springframework.stereotype.Service;
import ru.itis.hw45.account.dto.AccountResponse;
import ru.itis.hw45.account.dto.NewAccountRequest;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountResponse> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountResponse::from)
                .toList();
    }

    public AccountResponse create(NewAccountRequest request) {
        Account account = new Account();
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());

        return AccountResponse.from(accountRepository.save(account));
    }
}
