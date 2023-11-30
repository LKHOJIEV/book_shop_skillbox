package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.repository.AuthorRepository;
import com.example.MyBookShopApp.struct.author.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorsService {

    //private JdbcTemplate jdbcTemplate;

    private AuthorRepository authorRepository;
    @Autowired
    public AuthorsService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }



    public Map<String, List<AuthorEntity>> getAuthorList() {

        /*List<Author> authors = jdbcTemplate.query(
                "select a.id,\n" +
                        "       a.photo,\n" +
                        "       a.slug,\n" +
                        "       a.name,\n" +
                        "       a.description \n" +
                        "from author a order by a.name",
                (ResultSet rs, int rownum)->{

                    Author author = new Author();
                    author.setId(rs.getInt("id"));
                    author.setPhoto(rs.getString("photo"));
                    author.setSlug(rs.getString("slug"));
                    author.setName(rs.getString("name"));
                    author.setDescription(rs.getString("description"));
            return author;
        });*/

         List<AuthorEntity> authorEntities = authorRepository.findAuthorEntities();

        //return authors.stream().collect(Collectors.groupingBy((Author a) ->{return  a.getName().substring(0,1);}));

        return  authorEntities
                .stream()
                .collect(Collectors
                        .groupingBy(
                                (AuthorEntity a) -> {return a.getName().substring(0,1);}));
    }

    public List<AuthorEntity> getAuthorByBookId(Integer id) {

        return authorRepository.findAuthorEntityByBookId(id);
    }
}
