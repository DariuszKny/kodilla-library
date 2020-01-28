package com.kodillalibrary.service;

import com.kodillalibrary.domain.UserDto;
import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.domain.User;
import com.kodillalibrary.mapper.UserMapper;
import com.kodillalibrary.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(final Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User saveUser(final UserDto userDto){
        userDto.setCreated(LocalDate.now());
        return userRepository.save(userMapper.mapToUser(userDto));
    }

    public void deleteUser(final Long aLong){
        userRepository.deleteById(aLong);
    }

}
