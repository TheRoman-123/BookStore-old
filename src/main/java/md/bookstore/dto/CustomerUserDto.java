package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUserDto {
    @NotBlank(message = "Please write your name")
    @Size(max = 20, message = "First name can't be longer than 20 symbols")
    @Pattern(
            regexp = "^[a-zA-Zа-яА-Я]+$",
            message = "Name can consist only of latin and cyrillic symbols"
    )
    private String firstName;
    @Size(max = 20, message = "Last name can't be longer than 20 symbols")
    @Pattern(
            regexp = "^[a-zA-Zа-яА-Я]+$",
            message = "Name can consist only of latin and cyrillic symbols"
    )
    private String lastName;
    private LocalDate birthDate;
    @Pattern(regexp = "0[67]\\d{7}", message = "Enter valid phone number!")
    @Size(max = 9)
    private String phoneNumber;
    @Email(message = "Please provide valid email")
    @Size(min = 6, max = 254, message = "Email too long")
    private String email;
    @NotBlank(message = "Please provide a password")
    @Size(min = 8, max = 50, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must have at least 1 number, 1 letter Uppercase and Lowercase and 1 special character"
    )
    private String password;
}
