package com.kodillalibrary.mapper;

import com.kodillalibrary.domain.User;
import com.kodillalibrary.domain.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private final RentMapper rentMapper;

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getCreated(),
                userDto.getRents().stream()
                        .map(rentMapper::mapToRent)
                        .collect(Collectors.toList())
        );
    }
    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreated(),
                user.getRents().stream()
                        .map(rentMapper::mapToRentDto)
                        .collect(Collectors.toList())
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

}
