package com.vapasi.demo.service;

import com.vapasi.demo.dto.MovieDto;
import com.vapasi.demo.entities.MovieEntity;
import com.vapasi.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }


    public List getMovies() {
        List<MovieDto> movieDtos = new ArrayList<>();
        for(MovieEntity movieEntity : repository.findAll()){
            movieDtos.add(MovieDto.dtoFrom(movieEntity));
        }
        return movieDtos;
    }

    public MovieDto saveMovie(MovieDto movieDto) {
        /*Optional<MovieDto> originalMovieEntity = getMovieById(movieDto.getId());
        if(originalMovieEntity.isPresent()) {
            movieDto.setId((originalMovieEntity.get()).getId());
        }*/
        MovieEntity movieEntity = MovieEntity.entityFrom(movieDto);
        MovieEntity savedMovieEntity = repository.save(movieEntity);
        return MovieDto.dtoFrom(savedMovieEntity);
    }

    public Optional<MovieDto> getMovieById(String id) {
        Optional<MovieEntity> movieEntity = repository.findById(id);
        return movieEntity.map(MovieDto::dtoFrom);
    }
    /*
    public Optional<MovieDto> getMovieByActorName(String actorName) {
        Optional<MovieEntity> movieEntity = Optional.ofNullable(repository.findByActorName(actorName));
        return movieEntity.map(MovieDto::dtoFrom);
    }*/
    public List<MovieDto> getMovieListByActorName(String actorName) {
        List<MovieDto> movieDtoList = new ArrayList<>();
        List<Optional<MovieEntity>> moviesEntity = repository.findByActorNameIgnoreCaseIsContaining(actorName);

        for(Optional<MovieEntity> optionalMovieEntity: moviesEntity){
            MovieEntity movieEntity = (MovieEntity)optionalMovieEntity.get();
            movieDtoList.add(MovieDto.dtoFrom(movieEntity));
            //(MovieDto)optionalMovieEntity.map(MovieDto::dtoFrom)
        }
        return movieDtoList;
    }
    public MovieDto updateMovie(MovieDto movieDto) {
        MovieEntity movieEntity = MovieEntity.entityFrom(movieDto);
        MovieEntity savedMovieEntity = repository.save(movieEntity);
        return MovieDto.dtoFrom(savedMovieEntity);
    }
}
