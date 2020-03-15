package com.kodillalibrary.mapper;

import com.kodillalibrary.domain.User;
import com.kodillalibrary.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getCreated(),
                userDto.getRents()
        );
    }
    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreated(),
                user.getRents()
        );
    }
    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList.stream()
                .map(u -> new UserDto(u.getId(),u.getFirstName(),u.getLastName(),u.getCreated(),u.getRents()))
                .collect(Collectors.toList());
    }

}
