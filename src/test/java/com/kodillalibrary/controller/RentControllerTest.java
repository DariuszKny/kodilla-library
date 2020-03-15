package com.kodillalibrary.controller;

import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.domain.*;
import com.kodillalibrary.mapper.RentMapper;
import com.kodillalibrary.service.BookCopyService;
import com.kodillalibrary.service.RentService;
import com.kodillalibrary.service.TitleService;
import com.kodillalibrary.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RentControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private BookCopyService bookCopyService;

    @Autowired
    private RentService rentService;

    @Test
    public void ShouldGetEmptyList() {
        //Given
        //When
        List<Rent> rents = rentService.getAllRents();
        //Then
        Assert.assertEquals(0,rents.size());
    }

    @Test
    public void ShouldAddRent() throws UserNotFoundException {
        //Given
      UserDto userDto = new UserDto(2L,"TestName","ss", LocalDate.now(),new ArrayList<>());
      userService.saveUser(userDto);
      TitleDto titleDto = new TitleDto(4L,"TestName","TEstAuthor",1000,new ArrayList<>());
      titleService.saveTitle(titleDto);
      BookCopyDto bookCopyDto = new BookCopyDto(7L,titleDto,BookCopyStatus.AVAILABLE);
      bookCopyService.saveBookCopy(bookCopyDto);
        //When
        rentService.addRent(new RentDto(1L,userDto,bookCopyDto,LocalDate.now(),null));
        List<Rent> rents = rentService.getAllRents();
        Rent rent = rentService.getRentById(2L);
        //Then
        Assert.assertTrue(rent.getId()==2);
    }

    @Test
    public void ShouldAddDateOfBackAndChangeStatusOfBookCopy() throws UserNotFoundException {
        UserDto userDto = new UserDto(1L,"TestName","ss", LocalDate.now(),new ArrayList<>());
        userService.saveUser(userDto);
        TitleDto titleDto = new TitleDto(3L,"TestName","TEstAuthor",1000,new ArrayList<>());
        titleService.saveTitle(titleDto);
        BookCopyDto bookCopyDto = new BookCopyDto(1L,titleDto,BookCopyStatus.AVAILABLE);
        bookCopyService.saveBookCopy(bookCopyDto);
        //When
        rentService.saveRent(new RentDto(1L,userDto,bookCopyDto,LocalDate.now(),null));
        List<Rent> rents = rentService.getAllRents();
        //Then
        Assert.assertEquals(LocalDate.now(),rentService.getRentById(1L).getRentedDate());
        Assert.assertEquals(BookCopyStatus.AVAILABLE,bookCopyService.getBookCopyById(5L).getStatus());
    }
}