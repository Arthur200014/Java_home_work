package ru.itis.hw47.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountDto create(AccountRequest request) {
        Account account = new Account();
        account.setName(request.getName());
        account.setEmail(request.getEmail());

        return AccountDto.from(accountRepository.save(account));
    }

    public List<AccountDto> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::from)
                .toList();
    }

    public Account getEntity(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    public AccountDto getById(Long id) {
        return AccountDto.from(getEntity(id));
    }
}
