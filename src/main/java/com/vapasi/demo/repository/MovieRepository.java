package com.vapasi.demo.repository;

import com.vapasi.demo.dto.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    public List<Movie> getMovies(){

        List<Movie> movieList = new ArrayList<>();
        Movie m1 = new Movie("Movie 1", "Actor 1","director 1");
        Movie m2 = new Movie("Movie 2", "Actor 2","director 2");
        Movie m3 = new Movie("Movie 3", "Actor 3","director 3");

        movieList.add(m1);
        movieList.add(m2);
        movieList.add(m3);

        return movieList;
    }
}
