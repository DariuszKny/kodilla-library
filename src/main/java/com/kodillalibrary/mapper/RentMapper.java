package com.kodillalibrary.mapper;

import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.Rent;
import com.kodillalibrary.domain.RentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RentMapper {

    private UserMapper userMapper;
    private BookCopyMapper bookCopyMapper;


    public Rent mapToRent(final RentDto rentDto){
        return new Rent(
                rentDto.getId(),
                rentDto.getUser(),
                rentDto.getBookCopy(),
                rentDto.getRentedDate(),
                rentDto.getReturnDate()
        );
    }
    public RentDto mapToRentDto(final Rent rent){
        return new RentDto(
                rent.getId(),
                rent.getUser(),
                rent.getBookCopy(),
                rent.getRentedDate(),
                rent.getRentedDate()
        );
    }
    public List<RentDto> mapToRentDtoList(final List<Rent> rentList){
        return rentList.stream()
                .map(u -> new RentDto(
                        u.getId(),
                        u.getUser(),
                        u.getBookCopy(),
                        u.getRentedDate(),
                        u.getReturnDate())
                )
                .collect(Collectors.toList());
    }
}
