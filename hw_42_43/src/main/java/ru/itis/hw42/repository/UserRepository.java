package ru.itis.hw42.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hw42.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
