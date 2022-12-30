package md.bookstore.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class UserDto {
    @Null
    private final Long id;

    @NotBlank(message = "Please provide a username")
    @Size(min = 4, max = 50, message = "Username is too long")
    @Pattern(regexp = "[a-zA-Z0-9]{4,50}", message = "Username must have only latin letters and numbers")
    private final String username;

    @NotBlank(message = "Please provide a password")
    @Size(min = 8, max = 50, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must have at least 1 number, 1 letter Uppercase and Lowercase and 1 special character"
    )
    private final String password;

//    private Set<Authority> authoritySet;
}
