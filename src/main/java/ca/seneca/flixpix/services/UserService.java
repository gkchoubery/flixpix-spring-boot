package ca.seneca.flixpix.services;

import ca.seneca.flixpix.models.User;
import ca.seneca.flixpix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

}
