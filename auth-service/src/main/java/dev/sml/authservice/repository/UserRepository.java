package dev.sml.authservice.repository;

import dev.sml.authservice.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    UserModel findUserByName(String name);
}