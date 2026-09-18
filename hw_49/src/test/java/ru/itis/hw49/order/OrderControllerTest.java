package ru.itis.hw49.order;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hw49.common.GlobalExceptionHandler;
import ru.itis.hw49.common.NotFoundException;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Import(GlobalExceptionHandler.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Nested
    class GetOrder {

        @Test
        void returns_full_order_json() throws Exception {
            LocalDateTime date = LocalDateTime.of(2026, 9, 18, 12, 0);
            OrderDto order = new OrderDto(7L, 1L, date);

            when(orderService.getById(7L)).thenReturn(order);

            mockMvc.perform(get("/api/v1/orders/7"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(7))
                    .andExpect(jsonPath("$.userId").value(1))
                    .andExpect(jsonPath("$.orderDate").value("2026-09-18T12:00:00"));
        }

        @Test
        void returns_404_when_order_not_found() throws Exception {
            when(orderService.getById(99L))
                    .thenThrow(new NotFoundException("Заказ 99 не найден"));

            mockMvc.perform(get("/api/v1/orders/99"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value("Заказ 99 не найден"))
                    .andExpect(jsonPath("$.timestamp").exists());
        }
    }

    @Nested
    class CreateOrder {

        @Test
        void creates_order() throws Exception {
            LocalDateTime date = LocalDateTime.of(2026, 9, 18, 12, 0);
            OrderDto order = new OrderDto(1L, 2L, date);

            when(orderService.create(any(NewOrderRequest.class))).thenReturn(order);

            String body = """
                    {
                      "userId": 2,
                      "orderDate": "2026-09-18T12:00:00"
                    }
                    """;

            mockMvc.perform(post("/api/v1/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.userId").value(2))
                    .andExpect(jsonPath("$.orderDate").value("2026-09-18T12:00:00"));
        }

        @Test
        void returns_validation_errors() throws Exception {
            mockMvc.perform(post("/api/v1/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").value("Ошибка валидации"))
                    .andExpect(jsonPath("$.timestamp").exists())
                    .andExpect(jsonPath("$.errors.userId").exists())
                    .andExpect(jsonPath("$.errors.orderDate").exists());
        }
    }
}
