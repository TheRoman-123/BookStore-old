package md.bookstore.service.jwt;

import io.jsonwebtoken.Claims;
import md.bookstore.dto.jwt.JwtAuthentication;
import md.bookstore.entity.Authority;
import md.bookstore.entity.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUtils {
    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setAuthorities(getAuthorities(claims));
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setEmail(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Authority> getAuthorities(Claims claims) {
        List<String> authorities = claims.get("authorities", List.class);
        if (authorities != null) {
            return authorities.stream()
                    .map(Role::valueOf)
                    .map(Authority::new)
                    .collect(Collectors.toSet());
        }
        return null;
    }
}
