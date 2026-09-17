package ru.itis.hw48.order;

import java.time.LocalDateTime;

public class OrderDto {

    private final Long id;
    private final Long userId;
    private final LocalDateTime orderDate;

    public OrderDto(Long id, Long userId, LocalDateTime orderDate) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
    }

    public static OrderDto from(Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                order.getOrderDate()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
