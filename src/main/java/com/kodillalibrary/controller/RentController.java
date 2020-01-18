package com.kodillalibrary.controller;


import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.Rent;
import com.kodillalibrary.domain.RentDto;
import com.kodillalibrary.mapper.RentMapper;
import com.kodillalibrary.service.DbBookCopyService;
import com.kodillalibrary.service.DbRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/rent")
public class RentController {

    @Autowired
    DbRentService dbRentService;

    @Autowired
    DbBookCopyService dbBookCopyService;

    @Autowired
    RentMapper rentMapper;

    @GetMapping(value = "getRents")
    public List<RentDto> getRents(){
        return rentMapper.mapToRentDtoList(dbRentService.getAllRents());
    }

    @PostMapping(value = "rentBook",consumes = APPLICATION_JSON_VALUE)
    public void addRent(@RequestBody RentDto rentDto) throws UserNotFoundExcepion {
        if(dbBookCopyService.getBookCopyById(rentDto.getBookCopy().getId()).getStatus().equals("Rented")){
            System.out.println("This copy is already rented");
        } else {
            rentDto.setRented(LocalDate.now());
            Rent rent = dbRentService.saveRent(rentMapper.mapToRent(rentDto));
            long bookCopyId = rent.getBookCopy().getId();
            BookCopy bookCopy = dbBookCopyService.changeStatus(bookCopyId);
            dbBookCopyService.saveBookCopy(bookCopy);
        }
    }

    @DeleteMapping(value = "deleteReny",consumes = APPLICATION_JSON_VALUE)
    public void deleteRent(@RequestParam long rentId){
        dbRentService.deleteRent(rentId);
    }

    @PutMapping(value = "backBook",consumes = APPLICATION_JSON_VALUE)
    public void endRent(@RequestParam Long rentId) throws UserNotFoundExcepion {
        BookCopy bookCopy = dbRentService.getRentById(rentId).getBookCopy();
        dbBookCopyService.changeStatus(bookCopy.getId());
        dbBookCopyService.saveBookCopy(bookCopy);
        Rent rent = dbRentService.getRentById(rentId);
        rent.setGotBack(LocalDate.now());
        dbRentService.saveRent(rent);
        System.out.println("The book has been back");
    }

}
