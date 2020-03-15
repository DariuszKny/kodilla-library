package com.kodillalibrary.mapper;

import com.kodillalibrary.domain.Title;
import com.kodillalibrary.domain.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleMapper {
    public Title mapToTitle(final TitleDto titleDto){
        return new Title(
                titleDto.getId(),
                titleDto.getTitleName(),
                titleDto.getTitleAuthor(),
                titleDto.getYearPublished(),
                titleDto.getBookCopies()
        );
    }
    public TitleDto mapToTitleDto(final Title title){
        return new TitleDto(
                title.getId(),
                title.getTitleName(),
                title.getTitleAuthor(),
                title.getYearPublished(),
                title.getBookCopies()
        );
    }
    public List<TitleDto> mapToTitleDtoList(final List<Title> titleList){
        return titleList.stream()
                .map(u -> new TitleDto(u.getId(),u.getTitleName(),u.getTitleAuthor(),u.getYearPublished(),u.getBookCopies()))
                .collect(Collectors.toList());
    }
}
