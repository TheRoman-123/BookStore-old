package md.bookstore.dto.converter;

import md.bookstore.dto.CustomerDto;
import md.bookstore.dto.CustomerUserDto;
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

    public static CustomerDto fromDto(CustomerUserDto customerUserDto) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customerUserDto.getFirstName());
        customerDto.setLastName(customerUserDto.getLastName());
        customerDto.setBirthDate(customerUserDto.getBirthDate());
        customerDto.setPhoneNumber(customerUserDto.getPhoneNumber());
        return customerDto;
    }
}
