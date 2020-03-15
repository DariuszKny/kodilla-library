package com.kodillalibrary.controller;

import com.kodillalibrary.exceptions.BookCopyNotFoundException;
import com.kodillalibrary.domain.BookCopyDto;
import com.kodillalibrary.service.BookCopyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/bookCopy")
@AllArgsConstructor
@Slf4j
public class BookCopyController {

    private final BookCopyService bookCopyService;


    @PostMapping(value = "addCopy")
    public void addTitle(@RequestBody BookCopyDto bookCopyDto){
        bookCopyService.saveBookCopy(bookCopyDto);
    }

    @GetMapping(value = "showHowManyCopiesAvailable")
    public long showHowManyCopies(@RequestParam Long titleId){
       return bookCopyService.getCount(titleId);
    }

    @DeleteMapping(value = "deleteCopy")
    public void deleteCopy(@RequestParam long bookCopyId){
        bookCopyService.deleteBookCopy(bookCopyId);
    }

    @PutMapping(value = "changeStatus")
    public void changeStatus(@RequestParam Long bookCopyId) throws BookCopyNotFoundException {
       bookCopyService.changeStatus(bookCopyId);
    }

}
