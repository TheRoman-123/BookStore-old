package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.dao.CustomerDAO;
import md.bookstore.dto.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerDTO getUserById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("User id invalid!");
        }
        return new CustomerDTO(customerDAO.getReferenceById(id));
    }
}
