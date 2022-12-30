package md.bookstore.dto.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {
    private String email;
    private String password;
    private Boolean rememberMe;
}
