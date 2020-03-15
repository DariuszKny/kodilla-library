package com.kodillalibrary.service;

import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.BookCopyStatus;
import com.kodillalibrary.domain.RentDto;
import com.kodillalibrary.exceptions.RentNotFoundException;
import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.domain.Rent;
import com.kodillalibrary.mapper.RentMapper;
import com.kodillalibrary.repository.RentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RentService {

    private RentRepository rentRepository;
    private BookCopyService bookCopyService;
    private RentMapper rentMapper;

    public List<Rent> getAllRents(){
        return rentRepository.findAll();
    }

    public Rent getRentById(final Long rentId) throws RentNotFoundException {
        return rentRepository.findById(rentId).orElseThrow(() -> new RentNotFoundException(rentId));
    }

    public Rent saveRent(final RentDto rentDto){
        return rentRepository.save(rentMapper.mapToRent(rentDto));
    }

    public void deleteRent(final Long rentId){
        rentRepository.deleteById(rentId);
    }

    public void addRent(RentDto rentDto) throws RentNotFoundException {
        if (bookCopyService.getBookCopyById(rentDto.getBookCopy().getId()).getStatus() == BookCopyStatus.RENTED) {
            log.info("This copy is already rented!");
        } else {
            rentDto.setRentedDate(LocalDate.now());
            Rent rent = saveRent(rentDto);
            long bookCopyId = rent.getBookCopy().getId();
            bookCopyService.changeStatus(bookCopyId);
        }
    }
    public void endRent(Long rentId) throws RentNotFoundException {
        BookCopy bookCopy = getRentById(rentId).getBookCopy();
        bookCopyService.changeStatus(bookCopy.getId());
        Rent rent = getRentById(rentId);
        rent.setReturnDate(LocalDate.now());
        saveRent(rentMapper.mapToRentDto(rent));
        log.info("The book has been back");
    }
}
