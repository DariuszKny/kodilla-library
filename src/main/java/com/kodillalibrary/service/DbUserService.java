package com.kodillalibrary.service;

import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.domain.User;
import com.kodillalibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbUserService {
    @Autowired
    public UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(final Long aLong) throws UserNotFoundException {
        return userRepository.findById(aLong).orElseThrow(() -> new UserNotFoundException());
    }

    public User saveUser(final User task){
        return userRepository.save(task);
    }

    public void deleteUser(final Long aLong){
        userRepository.deleteById(aLong);
    }

}
