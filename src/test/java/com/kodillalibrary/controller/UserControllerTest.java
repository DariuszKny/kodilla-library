package com.kodillalibrary.controller;

import com.kodillalibrary.domain.User;
import com.kodillalibrary.domain.UserDto;
import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.mapper.UserMapper;
import com.kodillalibrary.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void shouldGetEmptyList() {
        //Given
        //When
        List<UserDto> users = userController.getUsers();
        //Then
        Assert.assertEquals(0,users.size());
    }
    @Test
    public void addUser() throws UserNotFoundException {
        //Given
        UserDto userDto = new UserDto(1L,"Dariusz","Knysak", LocalDate.now(),new ArrayList<>());
        //When
        userRepository.save(userMapper.mapToUser(userDto));
        //Then
        Long id = userDto.getId();
        Optional<User> user = userRepository.findById(id) ;
        Assert.assertTrue(user.isPresent());
    }
}