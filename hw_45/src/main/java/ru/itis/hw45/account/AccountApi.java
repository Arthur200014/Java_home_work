package ru.itis.hw45.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.hw45.account.dto.AccountResponse;
import ru.itis.hw45.account.dto.NewAccountRequest;

import java.util.List;

@Tag(name = "Accounts")
@RequestMapping("/api/v1/accounts")
public interface AccountApi {

    @Operation(summary = "Получить список аккаунтов")
    @ApiResponse(responseCode = "200", description = "Список аккаунтов")
    @GetMapping
    List<AccountResponse> getAccounts();

    @Operation(summary = "Создать аккаунт")
    @ApiResponse(responseCode = "201", description = "Аккаунт создан")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountResponse addAccount(@RequestBody NewAccountRequest request);
}
