package com.kodillalibrary.mapper;

import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.BookCopyDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookCopyMapper {

    private TitleMapper titleMapper;

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto){
        return new BookCopy(
                bookCopyDto.getId(),
                titleMapper.mapToTitle(bookCopyDto.getTitle()),
                bookCopyDto.getStatus()
        );
    }
    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy){
        return new BookCopyDto(
                bookCopy.getId(),
                titleMapper.mapToTitleDto(bookCopy.getTitle()),
                bookCopy.getStatus()
        );
    }
    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopyList){
        return bookCopyList.stream()
                .map(u -> new BookCopyDto(u.getId(),titleMapper.mapToTitleDto(u.getTitle()),u.getStatus()))
                .collect(Collectors.toList());
    }
}
