package md.bookstore.dto.converter;

import md.bookstore.dto.CustomerDto;
import md.bookstore.entity.Customer;

public class CustomerDtoConverter {
    public static Customer fromDto(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        return customer;
    }
}
