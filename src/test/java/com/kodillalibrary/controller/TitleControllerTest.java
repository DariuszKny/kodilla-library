package com.kodillalibrary.controller;

import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.domain.Title;
import com.kodillalibrary.domain.TitleDto;
import com.kodillalibrary.mapper.TitleMapper;
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
public class TitleControllerTest {

    @Autowired
    private TitleController titleController;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    public void shouldGetEmptyList() {
    //Given
        //When
        List<TitleDto> title = titleController.getTitles();
        //Then
        Assert.assertEquals(0,title.size());

    }
    @Test
    public void shouldAddTitle() throws UserNotFoundException {
        //Given
        Title someTitle = new Title(1L,"TestName","TestAuthor",1000,new ArrayList<>());
        //When
        titleRepository.save(someTitle);
        //Then
        Optional<Title> title = titleRepository.findById(5L) ;
        Assert.assertTrue(title.isPresent());
    }
}