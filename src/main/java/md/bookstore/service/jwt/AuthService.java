package md.bookstore.service.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import md.bookstore.dto.jwt.JwtAuthentication;
import md.bookstore.dto.jwt.JwtRequest;
import md.bookstore.dto.jwt.JwtResponse;
import md.bookstore.exception.AuthException;
import md.bookstore.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
//    Use MongoDB or Redis instead
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    public JwtResponse login(@NotNull JwtRequest authRequest) {
        if (refreshStorage.containsKey(authRequest.getEmail())) {
            throw new AuthException("User already in system");
        }
//        До момента истечения перенаправлять на /profile, /token и /refresh.
        final UserDetails user;
        try {
            user = userService.loadUserByUsername(authRequest.getEmail());
        } catch (UsernameNotFoundException e) {
            throw new AuthException("User not found");
        }

        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user, authRequest.getRememberMe());
            final String refreshToken = jwtProvider.generateRefreshToken(user, authRequest.getRememberMe());
            refreshStorage.put(user.getUsername(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    public JwtResponse getAccessToken(@NotNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String email = claims.getSubject();
            Boolean rememberMe = (Boolean) claims.get("rememberMe");
            String saveRefreshToken = refreshStorage.get(email);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                UserDetails user;
                try {
                    user = userService.loadUserByUsername(email);
                } catch (UsernameNotFoundException e) {
                    throw new AuthException("User not found");
                }
                String accessToken = jwtProvider.generateAccessToken(user, rememberMe);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(@NotNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String email = claims.getSubject();
            Boolean rememberMe = (Boolean) claims.get("rememberMe");
            String saveRefreshToken = refreshStorage.get(email);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                UserDetails user;
                try {
                    user = userService.loadUserByUsername(email);
                } catch (UsernameNotFoundException e) {
                    throw new AuthException("User not found");
                }
                String accessToken = jwtProvider.generateAccessToken(user, rememberMe);
                String newRefreshToken = jwtProvider.generateRefreshToken(user, rememberMe);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid refresh token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
