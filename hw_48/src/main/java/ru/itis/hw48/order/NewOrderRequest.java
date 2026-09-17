package ru.itis.hw48.order;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class NewOrderRequest {

    @NotNull(message = "Нужно указать пользователя")
    private Long userId;

    @NotNull(message = "Нужно указать дату заказа")
    private LocalDateTime orderDate;

    public NewOrderRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
