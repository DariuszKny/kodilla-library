package com.kodillalibrary.service;

import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.Rent;
import com.kodillalibrary.domain.RentDto;
import com.kodillalibrary.mapper.RentMapper;
import com.kodillalibrary.repository.RentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RentService {

    private final RentRepository rentRepository;
    private final BookCopyService bookCopyService;
    private final RentMapper rentMapper;

    public void addRent(RentDto rentDto){
        if(bookCopyService.getBookCopyById(rentDto.getBookCopy().getId()).getStatus().equals("Rented")){
            System.out.println("This copy is already rented");
        } else {
            rentDto.setRented(LocalDate.now());
            Rent rent = this.saveRent(rentMapper.mapToRent(rentDto));
            long bookCopyId = rent.getBookCopy().getId();
            bookCopyService.changeStatus(bookCopyId);
        }

    }

    public void endRent(long rentId){
        BookCopy bookCopy = this.getRentById(rentId).getBookCopy();
        bookCopyService.changeStatus(bookCopy.getId());

        Rent rent = this.getRentById(rentId);
        rent.setReturnDate(LocalDate.now());
        rentRepository.save(rent);

        log.info("The book has been back");
    }


    public List<Rent> getAllRents(){
        return rentRepository.findAll();
    }

    public Rent getRentById(final Long aLong)  {
        return rentRepository.findById(aLong).orElseThrow(() -> new UserNotFoundException());
    }

    public Rent saveRent(final Rent rent){
        return rentRepository.save(rent);
    }

    public void deleteRent(final Long aLong){
        rentRepository.deleteById(aLong);
    }
}
