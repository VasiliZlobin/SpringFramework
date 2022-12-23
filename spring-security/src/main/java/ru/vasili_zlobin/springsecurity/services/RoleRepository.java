package ru.vasili_zlobin.springsecurity.services;

import org.springframework.data.repository.CrudRepository;
import ru.vasili_zlobin.springsecurity.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
