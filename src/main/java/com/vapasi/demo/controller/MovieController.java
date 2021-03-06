package com.vapasi.demo.controller;

import com.vapasi.demo.dto.MovieDto;
import com.vapasi.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {


    private MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieDto>> getMovies() {
        List<MovieDto> movieDtoList = movieService.getMovies();
        return ResponseEntity.ok().body(movieDtoList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable String id, @RequestBody MovieDto movieDto) {
        MovieDto movie = movieService.getMovieById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found for this id :: " + id));
        movie.setId(id);
        if(movieDto.getName() != null)
            movie.setName(movieDto.getName());
        if(movieDto.getActorName() != null)
            movie.setActorName(movieDto.getActorName());
        if(movieDto.getDirectorName() != null)
            movie.setDirectorName(movieDto.getDirectorName());
        MovieDto updatedMovieDto = movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedMovieDto);
    }
    @PostMapping("/")
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        MovieDto savedMovieDto = movieService.saveMovie(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovieDto);
    }
    @GetMapping("/searchByActor")
    public ResponseEntity<List<MovieDto>> getMovieByActorName(@RequestParam(value="actorName") String actorName) {
        List<MovieDto> movie = movieService.getMovieListByActorName(actorName);
        if (movie.size() != 0) {
            return ResponseEntity.ok().body(movie);
        }
        return ResponseEntity.notFound().build();
    }
/*
    @GetMapping("/searchByActor")
    public ResponseEntity<MovieDto> getMovieByActorName(@RequestParam(value="actorName") String actorName) {
        Optional<MovieDto> movie = movieService.getMovieByActorName(actorName);
        if (movie.isPresent()) {
            return ResponseEntity.ok().body(movie.get());
        }
        return ResponseEntity.notFound().build();
    }
*/
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieForId(@PathVariable String id) {
        Optional<MovieDto> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok().body(movie.get());
        }
        return ResponseEntity.notFound().build();
    }
}
