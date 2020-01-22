package com.kodillalibrary.mapper;

import com.kodillalibrary.domain.Rent;
import com.kodillalibrary.domain.RentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RentMapper {

    private BookCopyMapper bookCopyMapper;

    public Rent mapToRent(final RentDto rentDto){
        return new Rent(
                rentDto.getId(),
                rentDto.getUser(),
                bookCopyMapper.mapToBookCopy(rentDto.getBookCopy()),
                rentDto.getRented(),
                rentDto.getGotBack()
        );
    }
    public RentDto mapToRentDto(final Rent rent){
        return new RentDto(
                rent.getId(),
                rent.getUser(),
                bookCopyMapper.mapToBookCopyDto(rent.getBookCopy()),
                rent.getRented(),
                rent.getReturnDate()
        );
    }
    public List<RentDto> mapToRentDtoList(final List<Rent> rentList){
        return rentList.stream()
                .map(u -> new RentDto(
                        u.getId(),
                        u.getUser(),
                        bookCopyMapper.mapToBookCopyDto(u.getBookCopy()),
                        u.getRented(),
                        u.getReturnDate()))
                .collect(Collectors.toList());
    }
}
