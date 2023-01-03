package md.bookstore.dto.converter;

import md.bookstore.dto.CustomerUserDto;
import md.bookstore.dto.UserDto;
import md.bookstore.entity.User;

public class UserDtoConverter {
    public static User fromDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto fromDto(CustomerUserDto customerUserDto) {
        return new UserDto(customerUserDto.getEmail(), customerUserDto.getPassword());
    }
}
