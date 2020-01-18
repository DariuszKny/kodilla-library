package com.kodillalibrary.controller;

import com.kodillalibrary.domain.*;
import com.kodillalibrary.mapper.RentMapper;
import com.kodillalibrary.repository.BookCopyRepository;
import com.kodillalibrary.repository.RentRepository;
import com.kodillalibrary.repository.TitleRepository;
import com.kodillalibrary.repository.UserRepository;
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
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RentControllerTest {

    @Autowired
    private RentController rentController;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private RentMapper rentMapper;

    @Test
    public void ShouldGetEmptyList() {
        //Given
        //When
        List<RentDto> rentDto = rentController.getRents();
        //Then
        Assert.assertEquals(0,rentDto.size());
    }

    @Test
    public void ShouldAddRent() throws UserNotFoundExcepion  {
        //Given
      User user =  userRepository.save(new User(1L,"TestName","ss", LocalDate.now(),new ArrayList<>()));
      Title title = titleRepository.save(new Title(1L,"TestName","TEstAuthor",1000,new ArrayList<>()));
      BookCopy bookCopy = bookCopyRepository.save(new BookCopy(1L,title,"Available"));

        //When
        rentController.addRent(new RentDto(1L,user,bookCopy,LocalDate.now(),null));
        List<Rent> rents = rentRepository.findAll();
        Optional<Rent> rent1 = rentRepository.findById(2L) ;
        //Then
        Assert.assertTrue(rent1.isPresent());
    }

    @Test
    public void ShouldAddDateOfBackAndChangeStatusOfBookCopy() throws UserNotFoundExcepion {
        User user =  userRepository.save(new User(1L,"TestName","ss", LocalDate.now(),new ArrayList<>()));
        Title title = titleRepository.save(new Title(1L,"TestName","TEstAuthor",1000,new ArrayList<>()));
        BookCopy bookCopy = bookCopyRepository.save(new BookCopy(1L,title,"Available"));
        //When
        rentController.addRent(new RentDto(1L,user,bookCopy,LocalDate.now(),null));

        rentController.endRent(rentRepository.findById(1L).get().getId());
        List<Rent> rents = rentRepository.findAll();
        //Then
        Assert.assertEquals(LocalDate.now(),rentRepository.findById(1L).get().getGotBack());
        Assert.assertEquals("Available",rentRepository.findById(1L).get().getBookCopy().getStatus());
    }
}