package ru.itis.hw49.user;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itis.hw49.common.NotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Nested
    class CreateUser {

        @Test
        void returns_saved_user() {
            NewUserRequest request = new NewUserRequest(
                    "Arthur",
                    "Sarifullin",
                    "arthur@mail.ru",
                    "Pass123",
                    20
            );

            User savedUser = new User(
                    1L,
                    "Arthur",
                    "Sarifullin",
                    "arthur@mail.ru",
                    "Pass123",
                    20
            );

            when(userRepository.save(any(User.class))).thenReturn(savedUser);

            UserDto result = userService.create(request);

            assertEquals(1L, result.getId());
            assertEquals("Arthur", result.getFirstName());
            assertEquals("Sarifullin", result.getLastName());
            assertEquals("arthur@mail.ru", result.getEmail());
            assertEquals(20, result.getAge());
            verify(userRepository).save(any(User.class));
        }
    }

    @Nested
    class GetUser {

        @Test
        void returns_user_by_id() {
            User user = new User(
                    1L,
                    "Arthur",
                    "Sarifullin",
                    "arthur@mail.ru",
                    "Pass123",
                    20
            );

            when(userRepository.findById(1L)).thenReturn(Optional.of(user));

            UserDto result = userService.getById(1L);

            assertEquals(1L, result.getId());
            assertEquals("Arthur", result.getFirstName());
            assertEquals("arthur@mail.ru", result.getEmail());
        }

        @Test
        void throws_when_user_not_found() {
            when(userRepository.findById(99L)).thenReturn(Optional.empty());

            NotFoundException exception = assertThrows(
                    NotFoundException.class,
                    () -> userService.getById(99L)
            );

            assertEquals("Пользователь 99 не найден", exception.getMessage());
        }
    }
}
