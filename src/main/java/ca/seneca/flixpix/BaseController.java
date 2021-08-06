package ca.seneca.flixpix;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    protected <T> ResponseEntity<T> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    protected <T> ResponseEntity<T> notAuthorized() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    protected <T> ResponseEntity<T> badRequest() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    protected <T> ResponseEntity<T> duplicate() {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
