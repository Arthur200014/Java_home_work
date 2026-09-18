package ru.itis.hw49.user;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(GlobalExceptionHandler.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Nested
    class GetUser {

        @Test
        void returns_full_user_json() throws Exception {
            UserDto user = new UserDto(
                    1L,
                    "Arthur",
                    "Sarifullin",
                    "arthur@mail.ru",
                    20
            );

            when(userService.getById(1L)).thenReturn(user);

            mockMvc.perform(get("/api/v1/users/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Arthur"))
                    .andExpect(jsonPath("$.lastName").value("Sarifullin"))
                    .andExpect(jsonPath("$.email").value("arthur@mail.ru"))
                    .andExpect(jsonPath("$.age").value(20))
                    .andExpect(jsonPath("$.password").doesNotExist());
        }

        @Test
        void returns_404_when_user_not_found() throws Exception {
            when(userService.getById(99L))
                    .thenThrow(new NotFoundException("Пользователь 99 не найден"));

            mockMvc.perform(get("/api/v1/users/99"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value("Пользователь 99 не найден"))
                    .andExpect(jsonPath("$.timestamp").exists());
        }
    }

    @Nested
    class CreateUser {

        @Test
        void creates_user_and_returns_full_json() throws Exception {
            UserDto user = new UserDto(
                    1L,
                    "Arthur",
                    "Sarifullin",
                    "arthur@mail.ru",
                    20
            );

            when(userService.create(any(NewUserRequest.class))).thenReturn(user);

            String body = """
                    {
                      "firstName": "Arthur",
                      "lastName": "Sarifullin",
                      "email": "arthur@mail.ru",
                      "password": "Pass123",
                      "age": 20
                    }
                    """;

            mockMvc.perform(post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Arthur"))
                    .andExpect(jsonPath("$.lastName").value("Sarifullin"))
                    .andExpect(jsonPath("$.email").value("arthur@mail.ru"))
                    .andExpect(jsonPath("$.age").value(20))
                    .andExpect(jsonPath("$.password").doesNotExist());
        }

        @Test
        void returns_validation_errors() throws Exception {
            String body = """
                    {
                      "firstName": "",
                      "lastName": "",
                      "email": "wrong-email",
                      "password": "123",
                      "age": 150
                    }
                    """;

            mockMvc.perform(post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(body))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").value("Ошибка валидации"))
                    .andExpect(jsonPath("$.timestamp").exists())
                    .andExpect(jsonPath("$.errors.firstName").exists())
                    .andExpect(jsonPath("$.errors.lastName").exists())
                    .andExpect(jsonPath("$.errors.email").exists())
                    .andExpect(jsonPath("$.errors.password").exists())
                    .andExpect(jsonPath("$.errors.age").exists());
        }
    }
}
