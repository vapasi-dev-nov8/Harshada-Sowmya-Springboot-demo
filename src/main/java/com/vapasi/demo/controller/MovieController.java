package com.vapasi.demo.controller;

import com.vapasi.demo.dto.Movie;
import com.vapasi.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/")

    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.saveMovie(movie);
        return ResponseEntity.ok().body(savedMovie);
    }
    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }
}
