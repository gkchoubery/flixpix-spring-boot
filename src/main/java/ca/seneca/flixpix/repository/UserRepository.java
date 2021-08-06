package ca.seneca.flixpix.repository;

import ca.seneca.flixpix.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmailIgnoreCase(String email);
}
