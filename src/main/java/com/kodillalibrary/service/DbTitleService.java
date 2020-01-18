package com.kodillalibrary.service;

import com.kodillalibrary.controller.UserNotFoundExcepion;
import com.kodillalibrary.domain.Title;
import com.kodillalibrary.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbTitleService {
    @Autowired
    public TitleRepository titleRepository;

    public List<Title> getAllTitles(){
        return titleRepository.findAll();
    }

    public Title getTitleById(final Long aLong) throws UserNotFoundExcepion {
        return titleRepository.findById(aLong).orElseThrow(() -> new UserNotFoundExcepion());
    }

    public Title saveTitle(final Title title){
        return titleRepository.save(title);
    }

    public void deleteTitle(final Long aLong){
        titleRepository.deleteById(aLong);
    }
}
