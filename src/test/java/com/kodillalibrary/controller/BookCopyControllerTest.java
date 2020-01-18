package com.kodillalibrary.controller;

import com.kodillalibrary.domain.BookCopy;
import com.kodillalibrary.domain.BookCopyDto;
import com.kodillalibrary.domain.Title;
import com.kodillalibrary.mapper.BookCopyMapper;
import com.kodillalibrary.mapper.TitleMapper;
import com.kodillalibrary.repository.BookCopyRepository;
import com.kodillalibrary.repository.TitleRepository;
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
    private TitleRepository titleRepository;

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Autowired
    private TitleMapper titleMapper;

    @Test
    public void ShouldAddBookCopy()  throws UserNotFoundExcepion {
        //Given
        Title title = new Title(2L,"TestName","TEstAuthor",1000,new ArrayList<>());
        titleRepository.save(title);
        Title title2 = titleRepository.findById(2L).orElseThrow(() -> new UserNotFoundExcepion());
        BookCopy bookCopy = new BookCopy(1L,title2, "Available");
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
        Title title = new Title(1L,"TestName","TestAuthor",2000,new ArrayList<>());
        titleRepository.save(title);
        BookCopyDto bookCopyDto1 = new BookCopyDto(1L,title,"Available");
        BookCopyDto bookCopyDto2 = new BookCopyDto(2L,title,"Available");
        BookCopy bookCopy1 = bookCopyRepository.save(bookCopyMapper.mapToBookCopy(bookCopyDto1));
        BookCopy bookCopy2 = bookCopyRepository.save(bookCopyMapper.mapToBookCopy(bookCopyDto2));
        //When
        Long copies = bookCopyController.showHowManyCopies(title.getId());
        Long along = 2L;
        Assert.assertEquals(along,copies);
    }

    @Test
    public void ShouldChangeStatus() throws UserNotFoundExcepion {
        //Given
        BookCopyDto bookCopyDto = new BookCopyDto(1L,new Title(),"Available");
        BookCopy bookCopy = bookCopyRepository.save(bookCopyMapper.mapToBookCopy(bookCopyDto));
        //When
        bookCopyController.changeStatus(bookCopy.getId());
        String status = bookCopy.getStatus();
        //Then
        Assert.assertEquals("Rented",status);
    }
}