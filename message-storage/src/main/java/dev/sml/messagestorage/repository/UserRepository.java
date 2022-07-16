package dev.sml.messagestorage.repository;

import dev.sml.messagestorage.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    UserModel findUserByName(String name);
}