package com.vapasi.demo.repository;

import com.vapasi.demo.entities.MovieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, String> {
   public List<Optional<MovieEntity>> findByActorNameIgnoreCaseIsContaining(String actorName);
}
