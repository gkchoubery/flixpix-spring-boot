package ca.seneca.flixpix.services;

import ca.seneca.flixpix.models.User;
import ca.seneca.flixpix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmailIgnoreCase(email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> oUser = userRepository.findUserByEmailIgnoreCase(s);
        if (oUser.isPresent()) {
            return oUser.get();
        }
        throw new UsernameNotFoundException(s);
    }
}
