package com.kodillalibrary.service;

import com.kodillalibrary.domain.TitleDto;
import com.kodillalibrary.exceptions.TitleNotFoundException;
import com.kodillalibrary.exceptions.UserNotFoundException;
import com.kodillalibrary.domain.Title;
import com.kodillalibrary.mapper.TitleMapper;
import com.kodillalibrary.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TitleService {


    private TitleRepository titleRepository;
    private TitleMapper titleMapper;

    public List<Title> getAllTitles(){
        return titleRepository.findAll();
    }

    public Title getTitleById(final Long titleId) throws UserNotFoundException {
        return titleRepository.findById(titleId).orElseThrow(() -> new TitleNotFoundException(titleId));
    }

    public Title saveTitle(final TitleDto titleDto){
        return titleRepository.save(titleMapper.mapToTitle(titleDto));
    }

    public void deleteTitle(final Long titleId){
        titleRepository.deleteById(titleId);
    }
}
