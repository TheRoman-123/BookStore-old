package md.bookstore.controller;

import lombok.AllArgsConstructor;
import md.bookstore.dto.CustomerUserDto;
import md.bookstore.exception.UserAlreadyExistAuthenticationException;
import md.bookstore.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("register")
public class RegisterController {
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Object> register(
            @RequestBody @Valid CustomerUserDto customerUserDto
    ) throws UserAlreadyExistAuthenticationException {
        return new ResponseEntity<>(customerService.saveCustomer(customerUserDto), HttpStatus.CREATED);
    }
}
