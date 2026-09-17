package ru.itis.hw47.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.hw47.account.Account;
import ru.itis.hw47.account.AccountService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AccountService accountService;

    public OrderService(OrderRepository orderRepository, AccountService accountService) {
        this.orderRepository = orderRepository;
        this.accountService = accountService;
    }

    @Transactional
    public OrderDto create(NewOrderRequest request) {
        Account account = accountService.getEntity(request.getAccountId());

        Order order = new Order();
        order.setAccount(account);
        order.setOrderDate(LocalDate.parse(request.getDate()));

        return OrderDto.from(orderRepository.save(order));
    }

    public List<OrderDto> findByAccount(Long accountId) {
        accountService.getEntity(accountId);

        return orderRepository.findAllByAccountId(accountId)
                .stream()
                .map(OrderDto::from)
                .toList();
    }
}
