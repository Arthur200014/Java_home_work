package ru.itis.hw48.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.hw48.common.NotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto create(NewUserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setAge(request.getAge());

        return UserDto.from(userRepository.save(user));
    }

    public UserDto getById(Long id) {
        return UserDto.from(getEntity(id));
    }

    public User getEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь " + id + " не найден"));
    }
}
