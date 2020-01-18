package com.kodillalibrary.controller;

import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.BookCopyDto;
import com.kodillalibrary.mapper.BookCopyMapper;
import com.kodillalibrary.service.DbBookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/bookCopy")
public class BookCopyController {

    @Autowired
    DbBookCopyService dbBookCopyService;

    @Autowired
    BookCopyMapper bookCopyMapper;


    @PostMapping(value = "addCopy",consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody BookCopyDto bookCopyDto){
        dbBookCopyService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    @GetMapping(value = "showHowManyCopiesAvailable")
    public long showHowManyCopies(@RequestParam Long titleId){
       return dbBookCopyService.getCount(titleId);
    }

    @DeleteMapping(value = "deleteCopy",consumes = APPLICATION_JSON_VALUE)
    public void deleteCopy(@RequestParam long bookCopyId){
        dbBookCopyService.deleteBookCopy(bookCopyId);
    }

    @PutMapping(value = "ChangeStatus",consumes = APPLICATION_JSON_VALUE)
    public void changeStatus(@RequestParam Long bookCopyId) throws UserNotFoundExcepion {
       BookCopy bookCopy = dbBookCopyService.changeStatus(bookCopyId);
       dbBookCopyService.saveBookCopy(bookCopy);
    }

}
