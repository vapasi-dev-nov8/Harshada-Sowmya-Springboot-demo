package com.vapasi.demo.service;



import com.vapasi.demo.dto.Movie;
import com.vapasi.demo.repository.MovieRepository;
import com.vapasi.demo.dto.Movie;
import com.vapasi.demo.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MoviesServiceTest {

    MovieService moviesService;

    MovieRepository movieRepository;


    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        moviesService = new MovieService(movieRepository);
    }

    @Test
    public void getAllMoviesAddedToList() {
        List<Movie> allMovies = new ArrayList<>();
        allMovies.add(new Movie(101, "pirates of caribbean", "Johny Depp", "Gore Verbinsky"));
        allMovies.add(new Movie(102, "The Fight club", "Brad pitt", "David Fincher"));
        allMovies.add(new Movie(103, "Interstellar", "Mathew McConaughey", "Christopher Nolan"));
        allMovies.add(new Movie(104, "pulp fiction", "Samuel L Johnson", "Quintine torentino"));
        when(movieRepository.getMovies()).thenReturn(allMovies);
        List<Movie> totalMovies = moviesService.getMovies();
        verify(movieRepository, times(1)).getMovies();
        assertEquals(allMovies, totalMovies);
    }

    @Test
    public void getEmptyMoviesWhenNoMoviesAddedToList() {
        List<Movie> allMovies = new ArrayList<>();
        when(movieRepository.getMovies()).thenReturn(allMovies);

        List<Movie> totalMovies = moviesService.getMovies();
    verify(movieRepository, times(1)).getMovies();
        assertEquals(allMovies.size(), totalMovies.size());
    }

    @Test
    public void shouldSaveMovieAndReturnSavedMovieDetails() {

        Movie movie = mock(Movie.class);
        Movie savedMovie = mock(Movie.class);

        when(movieRepository.saveMovie(movie)).thenReturn(savedMovie);

        Movie actualMovie = moviesService.saveMovie(movie);

        assertEquals(savedMovie, actualMovie);
    }
}