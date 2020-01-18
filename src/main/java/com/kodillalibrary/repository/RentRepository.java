package com.kodillalibrary.repository;

import com.kodillalibrary.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {

    @Override
    List<Rent> findAll();

    @Override
    Optional<Rent> findById(Long aLong);

    @Override
    Rent save(Rent rent);

    @Override
    void deleteById(Long aLong);
}
