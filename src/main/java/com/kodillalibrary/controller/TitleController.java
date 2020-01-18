package com.kodillalibrary.controller;

import com.kodillalibrary.domain.TitleDto;
import com.kodillalibrary.mapper.TitleMapper;
import com.kodillalibrary.service.DbTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/title")
public class TitleController {

    @Autowired
    DbTitleService dbTitleService;

    @Autowired
    TitleMapper titleMapper;

    @GetMapping(value = "getTitles")
    public List<TitleDto> getTitles(){
       return titleMapper.mapToTitleDtoList(dbTitleService.getAllTitles());
    }

    @PostMapping(value = "addTitle",consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto){
        dbTitleService.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @DeleteMapping(value = "deleteTitle",consumes = APPLICATION_JSON_VALUE)
    public void deleteTitle(@RequestParam long titleId){
        dbTitleService.deleteTitle(titleId);
    }


}
