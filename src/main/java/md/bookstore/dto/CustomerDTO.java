package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 * A DTO for the {@link md.bookstore.entity.Customer} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    @Size(max = 20)
    private String firstName;
    @Size(max = 20)
    private String lastName;
    private LocalDate birthDate;
    @Email
    @Size(max = 50)
    private String email;
    @Pattern(regexp = "0[67]\\d{7}", message = "Enter valid phone number!")
    @Size(max = 9)
    private String phoneNumber;

    public CustomerDTO(Customer customer) {
        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        birthDate = customer.getBirthDate();
        email = customer.getEmail();
        phoneNumber = customer.getPhoneNumber();
    }
}