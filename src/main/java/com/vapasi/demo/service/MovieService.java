package com.vapasi.demo.service;

import com.vapasi.demo.dto.Movie;
import com.vapasi.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }


    public List getMovies() {
        return repository.getMovies();
    }

    public Movie saveMovie(Movie movie) {
        return repository.saveMovie(movie);
    }
}
