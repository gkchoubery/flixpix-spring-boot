package ca.seneca.flixpix.controllers;

import ca.seneca.flixpix.BaseController;
import ca.seneca.flixpix.models.AuthLogin;
import ca.seneca.flixpix.models.User;
import ca.seneca.flixpix.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody AuthLogin user) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            return success(authService.findUserByEmail(user.getEmail()).get());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return notAuthorized();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        if (user.getFirstName().trim().length() < 3) {
            return badRequest();
        }
        if (user.getLastName().trim().length() < 3) {
            return badRequest();
        }
        if (user.getEmail().trim().length() < 5) {
            return badRequest();
        }
        if (user.getPassword().trim().length() < 8) {
            return badRequest();
        }
        if (authService.findUserByEmail(user.getEmail()).isPresent()) {
            return duplicate();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return success(authService.createUser(user));
    }
}
