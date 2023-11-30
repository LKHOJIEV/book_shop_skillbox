package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.repository.BookFileRepository;
import com.example.MyBookShopApp.struct.book.file.BookFileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookFileService {

    private BookFileTypeService bookFileTypeService;

    private BookFileRepository bookFileRepository;

    @Autowired
    public BookFileService(BookFileTypeService bookFileTypeService, BookFileRepository bookFileRepository) {
        this.bookFileTypeService = bookFileTypeService;
        this.bookFileRepository = bookFileRepository;
    }


    public List<BookFileEntity> bookFileEntities(Long id){
        return bookFileRepository.findBookFileEntitiesByBookId(id);
    }




}
