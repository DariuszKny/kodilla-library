package com.kodillalibrary.mapper;

import com.kodillalibrary.domain.Rent;
import com.kodillalibrary.domain.RentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {
    public Rent mapToRent(final RentDto rentDto){
        return new Rent(
                rentDto.getId(),
                rentDto.getUser(),
                rentDto.getBookCopy(),
                rentDto.getRented(),
                rentDto.getGotBack()
        );
    }
    public RentDto mapToRentDto(final Rent rent){
        return new RentDto(
                rent.getId(),
                rent.getUser(),
                rent.getBookCopy(),
                rent.getRented(),
                rent.getGotBack()
        );
    }
    public List<RentDto> mapToRentDtoList(final List<Rent> rentList){
        return rentList.stream()
                .map(u -> new RentDto(u.getId(),u.getUser(),u.getBookCopy(),u.getRented(),u.getGotBack()))
                .collect(Collectors.toList());
    }
}
