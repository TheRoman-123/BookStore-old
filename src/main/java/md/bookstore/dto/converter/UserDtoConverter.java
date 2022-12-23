package md.bookstore.dto.converter;

import md.bookstore.dto.UserDTO;
import md.bookstore.entity.Authority;
import md.bookstore.entity.User;

import java.util.Collections;

public class UserDtoConverter {
    public static User fromDto(UserDTO userDTO) {
        User user = new User();
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setAuthoritySet(Collections.singleton(Authority.USER));
        return user;
    }
}
