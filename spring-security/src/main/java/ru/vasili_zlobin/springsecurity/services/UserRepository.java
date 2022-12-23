package ru.vasili_zlobin.springsecurity.services;

import org.springframework.data.repository.CrudRepository;
import ru.vasili_zlobin.springsecurity.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String name);
}
