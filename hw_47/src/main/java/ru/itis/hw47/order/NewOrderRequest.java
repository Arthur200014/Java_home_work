package ru.itis.hw47.order;

public class NewOrderRequest {

    private Long accountId;
    private String date;

    public NewOrderRequest() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
