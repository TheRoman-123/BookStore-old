package md.bookstore.service;

import lombok.RequiredArgsConstructor;
import md.bookstore.dto.UserDto;
import md.bookstore.dto.converter.UserDtoConverter;
import md.bookstore.entity.User;
import md.bookstore.exception.UserAlreadyExistAuthenticationException;
import md.bookstore.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("User with username: %s not found", username)
                        )
                );
    }

    public void createUser(UserDto userDto) throws UserAlreadyExistAuthenticationException {
        if (userRepository.existsUserByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistAuthenticationException();
        }
        User user = UserDtoConverter.fromDto(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
