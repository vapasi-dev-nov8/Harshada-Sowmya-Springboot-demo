package com.vapasi.demo.repository;

import com.vapasi.demo.dto.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MovieRepository {

    List<Movie> movieList =
    new ArrayList<Movie>(
            Arrays.asList(
                    new Movie(101,"Movie 1", "Actor 1","director 1"),
                    new Movie(102,"Movie 2", "Actor 2","director 2"),
                    new Movie(103,"Movie 3", "Actor 3","director 3")
            ));

    public List<Movie> getMovies(){
        return movieList;
    }

    public Movie saveMovie(Movie movie) {
        System.out.println("Next Id:"+movieList.get(movieList.size()-1).getId() + 1);
        movie.setId(movieList.get(movieList.size()-1).getId() + 1);
        movieList.add(movie);
        return movie;
    }
}
