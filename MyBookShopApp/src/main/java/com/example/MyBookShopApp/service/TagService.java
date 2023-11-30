package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.dto.BookDataFullDto;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.TagRepository;
import com.example.MyBookShopApp.struct.tags.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    private final BookRepository bookRepository;

    @Autowired
    public TagService(TagRepository tagRepository, BookRepository bookRepository) {
        this.tagRepository = tagRepository;
        this.bookRepository = bookRepository;
    }

    public List<TagEntity> getAllTags() {
        return tagRepository.findAllBy();
    }


    public Page<BookDataFullDto> getPagedBooksByTagId(Long id, Integer offset, Integer limit) {

        Pageable page = PageRequest.of(offset, limit);
        return bookRepository.pagedBooksByTagId(id, page);
    }

    public Page<BookDataFullDto> getPagedBooksByTagName(String tagName, Integer offset, Integer limit) {

        Pageable page = PageRequest.of(offset, limit);
        return bookRepository.pagedBooksByTagName(tagName, page);
    }

    public TagEntity getTagById(Long id){
        return tagRepository.findById(id).isPresent() ? tagRepository.findById(id).get() : null ;
    }



}
