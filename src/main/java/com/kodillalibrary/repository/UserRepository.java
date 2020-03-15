package com.kodillalibrary.repository;

import com.kodillalibrary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long aLong);

    @Override
    User save(User user);

    @Override
    void deleteById(Long aLong);

}
