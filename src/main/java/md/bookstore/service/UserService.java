package md.bookstore.service;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.UserDto;
import md.bookstore.dto.converter.UserDtoConverter;
import md.bookstore.entity.Role;
import md.bookstore.entity.User;
import md.bookstore.exception.UserAlreadyExistAuthenticationException;
import md.bookstore.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findUserByUsername(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("User with email: %s not found", email)
                        )
                );
    }

    public User createUser(UserDto userDto)
            throws UserAlreadyExistAuthenticationException {
        if (userRepository.existsUserByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistAuthenticationException();
        }
        User user = UserDtoConverter.fromDto(userDto);
        // Tooooo looong!
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthoritySet(Collections.singleton(authorityService.getAuthorityByRole(Role.USER)));
        user.setEnabled(false); // Move it to Converter later if needed
        userRepository.save(user);
        return user;
    }

}
