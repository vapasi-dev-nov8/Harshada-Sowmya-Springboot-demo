package com.vapasi.demo.controller;

import com.vapasi.demo.dto.Movie;
import com.vapasi.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {


    private MovieService movieService;

    @GetMapping("/")
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movieList = movieService.getMovies();
        return ResponseEntity.ok().header("Header: Welcome","Movies").body(movieList);
    }

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }
}
