package com.kodillalibrary.controller;

import com.kodillalibrary.domain.Title;
import com.kodillalibrary.domain.TitleDto;
import com.kodillalibrary.exceptions.UserNotFoundException;
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

    @Autowired
    private TitleMapper titleMapper;


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
        TitleDto titleDto = new TitleDto(1L,"TestName","TEstAuthor",1000,new ArrayList<>());
        //When
        titleRepository.save(titleMapper.mapToTitle(titleDto));
        //Then
        Long id = titleDto.getId();
        Optional<Title> user = titleRepository.findById(id) ;
        Assert.assertTrue(user.isPresent());
    }
}