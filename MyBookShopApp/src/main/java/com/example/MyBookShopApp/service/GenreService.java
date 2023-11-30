package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.dto.GenreDto;
import com.example.MyBookShopApp.dto.GenreGenerationDto;
import com.example.MyBookShopApp.repository.GenreRepository;
import com.example.MyBookShopApp.struct.genre.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    //private List<GenreEntity> genreEntityList = genreRepository.findAll();

    public List<GenreEntity> getAllGenreGeneration() {

        return genreRepository.findAll();
    }

    public GenreEntity getGenreById(Long id) {
        return
                genreRepository.findById(id).isPresent()
                        ? genreRepository.findById(id).get()
                        : null;
    }

    public List<GenreDto> genreEntityListParents() {


        List<GenreDto> genreGeneration = new ArrayList<>();


        for (GenreEntity genreEntity : genreRepository.findAll()) {
            genreGeneration.add(
                    new GenreDto(
                            genreEntity.getId(),
                            generationParent(genreEntity.getParentId()),
                            genreEntity.getSlug(),
                            genreEntity.getName()));
        }

        return genreGeneration;

    }

    public List<GenreGenerationDto> genreEntityListChild() {

        List<GenreGenerationDto> genreGeneration = new ArrayList<>();

        for (GenreEntity genreEntity : genreRepository.getAllParentGenres()) {

            genreGeneration.add(
                    new GenreGenerationDto(
                            genreEntity.getId(),
                            generationChild(genreEntity.getId()),
                            genreEntity.getSlug(),
                            genreEntity.getName()
                    ));

        }


        return genreGeneration;

    }

    public GenreDto generationParent(Long parent_id) {

        if (genreRepository.findById(parent_id).isPresent()) {
            GenreEntity genreEntity = genreRepository.findById(parent_id).get();
            return new GenreDto(
                    genreEntity.getId(),
                    generationParent(genreEntity.getParentId()),
                    genreEntity.getSlug(),
                    genreEntity.getName());

        }
        return null;

    }

    public List<GenreGenerationDto> generationChild(Long parent_id) {

        List<GenreGenerationDto> genreGeneration = new ArrayList<>();

        if (!genreRepository.findGenreEntitiesByParentId(parent_id).isEmpty()) {
            for (GenreEntity entity : genreRepository.findGenreEntitiesByParentId(parent_id)) {
                genreGeneration.add(
                        new GenreGenerationDto(
                                entity.getId(),
                                generationChild(entity.getId()),
                                entity.getSlug(),
                                entity.getName())
                );
            }
            return genreGeneration;
        }
        return new ArrayList<>();
    }

}
