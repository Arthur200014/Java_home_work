package ru.itis.hw46.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.hw46.common.PageResponse;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountDto create(NewAccountDto dto) {
        Account account = new Account();
        account.setFirstName(dto.getFirstName());
        account.setLastName(dto.getLastName());
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());

        return AccountDto.from(accountRepository.save(account));
    }

    public PageResponse<AccountDto> findAll(int page, int size, String sort) {
        PageRequest request = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<Account> result = accountRepository.findAll(request);

        List<AccountDto> accounts = result.getContent()
                .stream()
                .map(AccountDto::from)
                .toList();

        return new PageResponse<>(
                accounts,
                result.getNumber(),
                result.getSize(),
                result.getTotalPages(),
                result.getTotalElements()
        );
    }

    public AccountDto findById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        return AccountDto.from(account);
    }
}
