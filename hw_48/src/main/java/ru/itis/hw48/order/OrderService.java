package ru.itis.hw48.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.hw48.common.NotFoundException;
import ru.itis.hw48.user.User;
import ru.itis.hw48.user.UserService;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Transactional
    public OrderDto create(NewOrderRequest request) {
        User user = userService.getEntity(request.getUserId());

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(request.getOrderDate());

        return OrderDto.from(orderRepository.save(order));
    }

    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Заказ " + id + " не найден"));

        return OrderDto.from(order);
    }
}
