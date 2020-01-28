package com.kodillalibrary.controller;

import com.kodillalibrary.exceptions.RentNotFoundException;
import com.kodillalibrary.domain.RentDto;
import com.kodillalibrary.mapper.RentMapper;
import com.kodillalibrary.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/rent")
public class RentController {

    private RentService rentService;
    private RentMapper rentMapper;

    @GetMapping(value = "getRents")
    public List<RentDto> getRents(){
        return rentMapper.mapToRentDtoList(rentService.getAllRents());
    }

    @PostMapping(value = "rentBook")
    public void addRent(@RequestBody RentDto rentDto) throws RentNotFoundException {
        rentService.addRent(rentDto);
    }

    @DeleteMapping(value = "deleteRent")
    public void deleteRent(@RequestParam long rentId){
        rentService.deleteRent(rentId);
    }

    @PutMapping(value = "endRent")
    public void endRent(@RequestParam Long rentId) throws RentNotFoundException {
        rentService.endRent(rentId);
    }

}
