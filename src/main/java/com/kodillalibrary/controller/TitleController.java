package com.kodillalibrary.controller;

import com.kodillalibrary.domain.TitleDto;
import com.kodillalibrary.mapper.TitleMapper;
import com.kodillalibrary.service.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/title")
public class TitleController {

    private TitleService titleService;
    private TitleMapper titleMapper;

    @GetMapping(value = "getTitles")
    public List<TitleDto> getTitles(){
       return titleMapper.mapToTitleDtoList(titleService.getAllTitles());
    }

    @PostMapping(value = "addTitle")
    public void addTitle(@RequestBody TitleDto titleDto){
        titleService.saveTitle(titleDto);
    }

    @DeleteMapping(value = "deleteTitle")
    public void deleteTitle(@RequestParam long titleId){
        titleService.deleteTitle(titleId);
    }

}
