package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.repository.BookFileTypeRepository;
import com.example.MyBookShopApp.struct.book.file.BookFileTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookFileTypeService {
    private final BookFileTypeRepository bookFileTypeRepository;

    @Autowired
    public BookFileTypeService(BookFileTypeRepository bookFileTypeRepository) {
        this.bookFileTypeRepository = bookFileTypeRepository;
    }

    public BookFileTypeEntity bookFileType(Integer id){
       return bookFileTypeRepository.findById(id);
    }


}
