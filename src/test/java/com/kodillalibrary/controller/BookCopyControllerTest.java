package com.kodillalibrary.controller;

import com.kodillalibrary.domain.*;
import com.kodillalibrary.exceptions.TitleNotFoundException;
import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.mapper.BookCopyMapper;
import com.kodillalibrary.repository.BookCopyRepository;
import com.kodillalibrary.service.TitleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookCopyControllerTest {

    @Autowired
    private BookCopyController bookCopyController;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private TitleService titleService;

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Test
    public void ShouldAddBookCopy()  throws TitleNotFoundException {
        //Given
        TitleDto titleDto = new TitleDto(2L,"TestName","TEstAuthor",1000,new ArrayList<>());
        titleService.saveTitle(titleDto);
        Title title2 = titleService.getTitleById(2L);
        BookCopy bookCopy = new BookCopy(1L,title2, BookCopyStatus.AVAILABLE);
        bookCopyRepository.save(bookCopy);
        //When
        //Then
        List<BookCopy> bookCopies = bookCopyRepository.findAll();
        Optional<BookCopy> bookCopy1 = bookCopyRepository.findById(4L) ;
        Assert.assertEquals(1,bookCopies.size());
        Assert.assertTrue(bookCopy1.isPresent());
    }

    @Test
    public void ShouldShowHowManyCopies() {
        //Given
        TitleDto titleDto = new TitleDto(1L,"TestName","TestAuthor",2000,new ArrayList<>());
        titleService.saveTitle(titleDto);
        BookCopyDto bookCopyDto1 = new BookCopyDto(1L,titleDto, BookCopyStatus.AVAILABLE);
        BookCopyDto bookCopyDto2 = new BookCopyDto(2L,titleDto,BookCopyStatus.AVAILABLE);
        BookCopy bookCopy1 = bookCopyRepository.save(bookCopyMapper.mapToBookCopy(bookCopyDto1));
        BookCopy bookCopy2 = bookCopyRepository.save(bookCopyMapper.mapToBookCopy(bookCopyDto2));
        //When
        Long copies = bookCopyController.showHowManyCopies(titleDto.getId());
        Long along = 2L;
        Assert.assertEquals(along,copies);
    }

    @Test
    public void ShouldChangeStatus() throws UserNotFoundException {
        //Given
        BookCopyDto bookCopyDto = new BookCopyDto(1L,new TitleDto(), BookCopyStatus.AVAILABLE);
        BookCopy bookCopy = bookCopyRepository.save(bookCopyMapper.mapToBookCopy(bookCopyDto));
        //When
        bookCopyController.changeStatus(bookCopy.getId());
        BookCopyStatus status = bookCopy.getStatus();
        //Then
        Assert.assertEquals(BookCopyStatus.RENTED,status);
    }
}