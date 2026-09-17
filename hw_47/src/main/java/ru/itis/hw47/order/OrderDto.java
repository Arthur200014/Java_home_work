package ru.itis.hw47.order;

public class OrderDto {

    private final Long id;
    private final Long accountId;
    private final String date;

    public OrderDto(Long id, Long accountId, String date) {
        this.id = id;
        this.accountId = accountId;
        this.date = date;
    }

    public static OrderDto from(Order order) {
        return new OrderDto(
                order.getId(),
                order.getAccount().getId(),
                order.getOrderDate().toString()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getDate() {
        return date;
    }
}
