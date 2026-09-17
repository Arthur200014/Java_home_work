package ru.itis.hw46.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.hw46.common.PageResponse;

@RequestMapping("/api/v1/accounts")
public interface AccountApi {

    @Operation(summary = "Получить страницу аккаунтов")
    @GetMapping
    PageResponse<AccountDto> getAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort
    );

    @Operation(summary = "Создать аккаунт")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountDto addAccount(@RequestBody NewAccountDto account);

    @Operation(summary = "Найти аккаунт по id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Аккаунт найден"),
            @ApiResponse(responseCode = "404", description = "Аккаунт не найден")
    })
    @GetMapping("/{id}")
    AccountDto getAccount(@PathVariable Long id);
}
