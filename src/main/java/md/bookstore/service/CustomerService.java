package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.repository.CustomerRepository;
import md.bookstore.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerDto getCustomerById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Customer id invalid!");
        }
        return new CustomerDto(customerRepository.getReferenceById(id));
    }
}
