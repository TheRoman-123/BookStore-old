package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dto.CustomerUserDto;
import md.bookstore.dto.UserDto;
import md.bookstore.dto.converter.CustomerDtoConverter;
import md.bookstore.dto.converter.UserDtoConverter;
import md.bookstore.entity.Customer;
import md.bookstore.entity.User;
import md.bookstore.exception.UserAlreadyExistAuthenticationException;
import md.bookstore.repository.CustomerRepository;
import md.bookstore.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    private UserService userService;

    public CustomerDto getCustomerById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Customer id invalid!");
        }
        return new CustomerDto(customerRepository.getReferenceById(id));
    }

    private Long saveCustomer(CustomerDto customerDto, UserDto userDto)
            throws UserAlreadyExistAuthenticationException {
        User user = userService.createUser(userDto);
        Customer customer = CustomerDtoConverter.fromDto(customerDto);
        customer.setUser(user);
        customerRepository.save(customer);
        return customer.getId();
    }

    public Long saveCustomer(CustomerUserDto customerUserDto)
            throws UserAlreadyExistAuthenticationException {
        return saveCustomer(
                CustomerDtoConverter.fromDto(customerUserDto),
                UserDtoConverter.fromDto(customerUserDto)
        );
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
