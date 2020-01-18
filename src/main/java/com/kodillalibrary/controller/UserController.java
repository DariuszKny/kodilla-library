package com.kodillalibrary.controller;

import com.kodillalibrary.domain.UserDto;
import com.kodillalibrary.mapper.UserMapper;
import com.kodillalibrary.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    DbUserService dbUserService;

    @Autowired
    UserMapper userMapper;

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers(){
        return userMapper.mapToUserDtoList(dbUserService.getAllUsers());
    }

    @PostMapping(value = "addUser",consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto){
        userDto.setCreated(LocalDate.now());
        dbUserService.saveUser(userMapper.mapToUser(userDto));
    }

    @DeleteMapping(value = "deleteUser",consumes = APPLICATION_JSON_VALUE)
    public void deleteTitle(@RequestParam long userId){
        dbUserService.deleteUser(userId);
    }

}
