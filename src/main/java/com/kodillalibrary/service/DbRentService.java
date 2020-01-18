package com.kodillalibrary.service;

import com.kodillalibrary.controller.UserNotFoundExcepion;
import com.kodillalibrary.domain.Rent;
import com.kodillalibrary.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbRentService {
    @Autowired
    public RentRepository rentRepository;

    public List<Rent> getAllRents(){
        return rentRepository.findAll();
    }

    public Rent getRentById(final Long aLong) throws UserNotFoundExcepion {
        return rentRepository.findById(aLong).orElseThrow(() -> new UserNotFoundExcepion());
    }

    public Rent saveRent(final Rent rent){
        return rentRepository.save(rent);
    }

    public void deleteRent(final Long aLong){
        rentRepository.deleteById(aLong);
    }
}
