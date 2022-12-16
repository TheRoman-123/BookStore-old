package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.CustomerRepository;
import md.bookstore.dto.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerDTO getUserById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("User id invalid!");
        }
        return new CustomerDTO(customerRepository.getReferenceById(id));
    }
}
