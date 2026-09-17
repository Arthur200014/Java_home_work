package ru.itis.hw47.order;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Добавить заказ аккаунту")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@RequestBody NewOrderRequest request) {
        return orderService.create(request);
    }

    @Operation(summary = "Заказы аккаунта")
    @GetMapping("/account/{accountId}")
    public List<OrderDto> getAccountOrders(@PathVariable Long accountId) {
        return orderService.findByAccount(accountId);
    }
}
