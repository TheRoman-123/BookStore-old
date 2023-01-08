package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.bookstore.entity.Customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * A DTO for the {@link md.bookstore.entity.Customer} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long id;
    @NotBlank(message = "Please write your name")
    @Size(max = 20, message = "First name can't be longer than 20 symbols")
    private String firstName;
    @Size(max = 20, message = "Last name can't be longer than 20 symbols")
    private String lastName;
    private LocalDate birthDate;
    @Pattern(regexp = "0[67]\\d{7}", message = "Enter valid phone number!")
    @Size(max = 9)
    private String phoneNumber;

    public CustomerDto(Customer customer) {
        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        birthDate = customer.getBirthDate();
        phoneNumber = customer.getPhoneNumber();
    }
}