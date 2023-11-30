package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.dto.RatingTitleDto;
import com.example.MyBookShopApp.repository.RatingRepository;
import com.example.MyBookShopApp.struct.book.rating.BookRatingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Map<Integer, Long> getRatingTitleStatics(String slug) {

        List<BookRatingEntity> bookRatingEntities = ratingRepository.findAllByBookSlug(slug);

        Map<Integer, Long> map = bookRatingEntities.
                stream().
                collect(Collectors.groupingBy(
                        BookRatingEntity::getValue, Collectors.counting()));


        map.put(0, map.values().stream().reduce(0L, Long::sum));

        long max = 0;
        long max_index = 0;
        for (int i = 1; i <= 5; i++) {
            if (max < (map.get(i) != null ? map.get(i) : 0L)) {
                max = map.get(i);
                max_index = i;
            }
        }

        map.put(6, max_index);

        return map;
    }


    public void saveBookRating(String slug, Integer value) {

        BookRatingEntity bookRatingEntity = new BookRatingEntity();
        bookRatingEntity.setBookSlug(slug);
        bookRatingEntity.setValue(value);
        ratingRepository.save(bookRatingEntity);

    }
}
