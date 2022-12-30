package md.bookstore.dto.converter;

import md.bookstore.dto.UserDto;
import md.bookstore.entity.Authority;
import md.bookstore.entity.Role;
import md.bookstore.entity.User;

import java.util.Collections;

public class UserDtoConverter {
    public static User fromDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAuthoritySet(Collections.singleton(new Authority(Role.USER)));
        return user;
    }
}
