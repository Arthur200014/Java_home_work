package ru.itis.hw49.order;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itis.hw49.common.NotFoundException;
import ru.itis.hw49.user.User;
import ru.itis.hw49.user.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderService orderService;

    @Nested
    class CreateOrder {

        @Test
        void creates_order_for_existing_user() {
            LocalDateTime date = LocalDateTime.of(2026, 9, 18, 12, 0);
            NewOrderRequest request = new NewOrderRequest(1L, date);
            User user = new User(1L, "Arthur", "Sarifullin", "arthur@mail.ru", "Pass123", 20);
            Order savedOrder = new Order(5L, date, user);

            when(userService.getEntity(1L)).thenReturn(user);
            when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

            OrderDto result = orderService.create(request);

            assertEquals(5L, result.getId());
            assertEquals(1L, result.getUserId());
            assertEquals(date, result.getOrderDate());
            verify(userService).getEntity(1L);
            verify(orderRepository).save(any(Order.class));
        }

        @Test
        void throws_when_user_not_found() {
            LocalDateTime date = LocalDateTime.of(2026, 9, 18, 12, 0);
            NewOrderRequest request = new NewOrderRequest(99L, date);

            when(userService.getEntity(99L))
                    .thenThrow(new NotFoundException("Пользователь 99 не найден"));

            assertThrows(NotFoundException.class, () -> orderService.create(request));
        }
    }

    @Nested
    class GetOrder {

        @Test
        void returns_order_by_id() {
            LocalDateTime date = LocalDateTime.of(2026, 9, 18, 12, 0);
            User user = new User(1L, "Arthur", "Sarifullin", "arthur@mail.ru", "Pass123", 20);
            Order order = new Order(3L, date, user);

            when(orderRepository.findById(3L)).thenReturn(Optional.of(order));

            OrderDto result = orderService.getById(3L);

            assertEquals(3L, result.getId());
            assertEquals(1L, result.getUserId());
            assertEquals(date, result.getOrderDate());
        }

        @Test
        void throws_when_order_not_found() {
            when(orderRepository.findById(55L)).thenReturn(Optional.empty());

            NotFoundException exception = assertThrows(
                    NotFoundException.class,
                    () -> orderService.getById(55L)
            );

            assertEquals("Заказ 55 не найден", exception.getMessage());
        }
    }
}
