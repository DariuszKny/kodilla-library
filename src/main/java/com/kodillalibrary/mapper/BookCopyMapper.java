package com.kodillalibrary.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.BookCopyDto;
import org.springframework.stereotype.Component;

@Component
public class BookCopyMapper {
    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getTitle(),
                bookCopyDto.getStatus()
        );
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getTitle(),
                bookCopy.getStatus()
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> bookCopyList) {
        return bookCopyList.stream()
                .map(u -> new BookCopyDto(u.getId(), u.getTitle(), u.getStatus()))
                .collect(Collectors.toList());
    }
}
