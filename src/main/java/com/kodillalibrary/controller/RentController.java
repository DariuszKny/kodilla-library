package com.kodillalibrary.controller;


import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.Rent;
import com.kodillalibrary.domain.RentDto;
import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.mapper.RentMapper;
import com.kodillalibrary.service.BookCopyService;
import com.kodillalibrary.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/rent")
public class RentController {

    @Autowired
    RentService rentService;

    @Autowired
    BookCopyService bookCopyService;

    @Autowired
    RentMapper rentMapper;

    @GetMapping(value = "getRents")
    public List<RentDto> getRents(){
        return rentMapper.mapToRentDtoList(rentService.getAllRents());
    }

    @PostMapping(value = "rentBook",consumes = APPLICATION_JSON_VALUE)
    public void addRent(@RequestBody RentDto rentDto) {
        rentService.addRent(rentDto);
    }

    @DeleteMapping(value = "deleteReny",consumes = APPLICATION_JSON_VALUE)
    public void deleteRent(@RequestParam long rentId){
        rentService.deleteRent(rentId);
    }

    @PutMapping(value = "backBook",consumes = APPLICATION_JSON_VALUE)
    public void endRent(@RequestParam Long rentId) throws UserNotFoundException {

        rentService.endRent(rentId);
    }

}
