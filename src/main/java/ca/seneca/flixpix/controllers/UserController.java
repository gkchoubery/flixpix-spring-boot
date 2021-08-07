package ca.seneca.flixpix.controllers;

import ca.seneca.flixpix.BaseController;
import ca.seneca.flixpix.models.User;
import ca.seneca.flixpix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<User> getUser(Authentication authentication) {
        return success((User) authentication.getPrincipal());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> login(@PathVariable("id") String id) {
        Optional<User> userData = userService.findById(id);
        if (userData.isPresent()) {
            return success(userData.get());
        }
        return notFound();
    }
}
