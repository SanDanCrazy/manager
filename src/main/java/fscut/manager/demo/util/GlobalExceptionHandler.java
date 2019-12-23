package fscut.manager.demo.util;

import fscut.manager.demo.exception.CustomerAlreadyExitsException;
import fscut.manager.demo.exception.CustomerNoAuthorityException;
import fscut.manager.demo.exception.CustomerNotExitsException;
import fscut.manager.demo.exception.InvalidTokenException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<String> unauthorizedHandler(UnauthorizedException e) throws IOException{
        return ResponseEntity.status(401).body("没有权限!" + e.getMessage());
    }

    @ExceptionHandler(value = CustomerAlreadyExitsException.class)
    public ResponseEntity<String> customerAlreadyExitsHandler(CustomerAlreadyExitsException e) throws IOException{
        return ResponseEntity.status(403).body(e.getMessage());
    }

    @ExceptionHandler(value = CustomerNotExitsException.class)
    public ResponseEntity<String> customerNotExitsHandler(CustomerNotExitsException e) throws IOException{
        return ResponseEntity.status(403).body(e.getMessage());
    }

    @ExceptionHandler(value = CustomerNoAuthorityException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> customerNoAuthorityHandler(CustomerNoAuthorityException e) {
        return ResponseEntity.status(401).body("没有权限！" + e.getMessage());
    }

    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<String> invalidTokenHandler(InvalidTokenException e) {
        return ResponseEntity.status(203).body("token已失效！" + e.getMessage());
    }

}
