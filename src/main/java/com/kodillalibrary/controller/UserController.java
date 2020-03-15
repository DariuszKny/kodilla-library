package com.kodillalibrary.controller;

import com.kodillalibrary.domain.UserDto;
import com.kodillalibrary.mapper.UserMapper;
import com.kodillalibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers(){
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @PostMapping(value = "addUser")
    public void addUser(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteTitle(@RequestParam long userId){
        userService.deleteUser(userId);
    }

}
