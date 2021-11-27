package com.vapasi.demo.service;


import com.vapasi.demo.dto.MovieDto;
import com.vapasi.demo.entities.MovieEntity;
import com.vapasi.demo.repository.MovieRepository;
import com.vapasi.demo.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class MovieServiceIntegrationTest {

    @Autowired
    MovieService moviesService;

    @Autowired
    MovieRepository moviesRepository;

    @Test
    public void getAllMoviesAddedToList() {
        moviesRepository.deleteAll();

        List<MovieEntity> allMovies = new ArrayList<>();
        allMovies.add(new MovieEntity(null, "pirates of caribbean", "Johny Depp", "Gore Verbinsky"));
        allMovies.add(new MovieEntity( null, "The Fight club", "Brad pitt", "David Fincher"));
        allMovies.add(new MovieEntity( null, "Interstellar", "Mathew McConaughey", "Christopher Nolan"));
        allMovies.add(new MovieEntity( null, "pulp fiction", "Samuel L Johnson", "Quintine torentino"));
        moviesRepository.saveAll(allMovies);

        List<MovieDto> actualMovies = moviesService.getMovies();

        assertEquals(4, actualMovies.size());
    }

    @Test
    public void getEmptyMoviesWhenNoMoviesAddedToList() {
        moviesRepository.deleteAll();

        List<MovieDto> allMovies = moviesService.getMovies();

        assertEquals(0, allMovies.size());
    }

    @Test
    void shouldSaveAndRetrieveMovie() {
        // Given
        moviesRepository.deleteAll();

        // When
        MovieEntity movieEntity = new MovieEntity(null, "test-movie", "test-actor", "test-director");
        MovieEntity savedEntity = moviesRepository.save(movieEntity);

        // Then
        MovieDto expectedMovie = new MovieDto(savedEntity.getId() ,"test-movie", "test-actor", "test-director");
        assertEquals(expectedMovie, moviesService.getMovieById(savedEntity.getId()).get());
    }
    @Test
    void shouldGetMovieByActorName() {
        // Given
        List<MovieDto> expectedMovieDtoList = new ArrayList<MovieDto>();
        MovieEntity movieEntity = new MovieEntity("1", "test-movie", "Shashi, Shammi, Ranbir, Raj, Prithviraj ", "test-director");
        expectedMovieDtoList.add(MovieDto.dtoFrom(movieEntity));
        moviesRepository.save(movieEntity);

        movieEntity = new MovieEntity("2", "test-movie", "Shammi, Randhir. Prithvi, Raj", "test-director");
        expectedMovieDtoList.add(MovieDto.dtoFrom(movieEntity));
        moviesRepository.save(movieEntity);

        List<Optional<MovieEntity>> moviesEntity = new ArrayList<>();

        // When
        List<MovieDto> actualMovieList = moviesService.getMovieListByActorName("Raj");
        // Then
        assertEquals(expectedMovieDtoList, actualMovieList);
        //When
        actualMovieList = moviesService.getMovieListByActorName("Shashi");
        //Then
        assertNotEquals(expectedMovieDtoList, actualMovieList);
    }
}
