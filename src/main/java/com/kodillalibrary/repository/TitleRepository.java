package com.kodillalibrary.repository;

import com.kodillalibrary.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TitleRepository extends JpaRepository<Title, Long> {

    @Override
    List<Title> findAll();

    @Override
    Optional<Title> findById(Long aLong);

    @Override
    Title save(Title title);

    @Override
    void deleteById(Long aLong);


}
