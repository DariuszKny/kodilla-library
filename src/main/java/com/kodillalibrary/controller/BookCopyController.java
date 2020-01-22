package com.kodillalibrary.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.BookCopyDto;
import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.mapper.BookCopyMapper;
import com.kodillalibrary.service.BookCopyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bookCopy")
@AllArgsConstructor
@Slf4j
public class BookCopyController {

    private final BookCopyService bookCopyService;
    private final BookCopyMapper bookCopyMapper;

    @PostMapping(value = "addCopy")
    public void addTitle(@RequestBody BookCopyDto bookCopyDto) {
        log.info("AddCopy called. {}", bookCopyDto);

        bookCopyService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    @GetMapping(value = "showHowManyCopiesAvailable")
    public long showHowManyCopies(@RequestParam Long titleId) {
        return bookCopyService.getCount(titleId);
    }

    @DeleteMapping(value = "deleteCopy", consumes = APPLICATION_JSON_VALUE)
    public void deleteCopy(@RequestParam long bookCopyId) {
        bookCopyService.deleteBookCopy(bookCopyId);
    }

    @PutMapping(value = "changeStatus", consumes = APPLICATION_JSON_VALUE)
    public void changeStatus(@RequestParam Long bookCopyId) throws UserNotFoundException {
        bookCopyService.changeStatus(bookCopyId);
    }

}
