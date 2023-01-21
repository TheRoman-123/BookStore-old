package md.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import md.bookstore.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserDto {
    @Email(message = "Please provide valid email")
    @Size(min = 6, max = 254, message = "Email too long")
    private String username;

    @NotBlank(message = "Please provide a password")
    @Size(min = 8, max = 50, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must have at least 1 number, 1 letter Uppercase and Lowercase and 1 special character"
    )
    private String password;

    public UserDto(User user) {
        username = user.getUsername();
        password = user.getPassword();
    }
}
