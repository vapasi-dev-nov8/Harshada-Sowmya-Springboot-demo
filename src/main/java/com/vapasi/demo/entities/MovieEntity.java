package com.vapasi.demo.entities;


import com.vapasi.demo.dto.MovieDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Movies")
//@NamedQuery(name="MovieEntity.findByActorNameLike", query="SELECT * FROM Movies WHERE ActorName like")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    String name;
    String actorName;
    String directorName;
    public MovieEntity(){

    }
    public MovieEntity(String id, String name, String actorName, String directorName) {
        this.id = id;
        this.name = name;
        this.actorName = actorName;
        this.directorName = directorName;
    }
    public static MovieEntity entityFrom(MovieDto movieDto) {
        return new MovieEntity(movieDto.getId(), movieDto.getName(), movieDto.getActorName(), movieDto.getDirectorName());
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    @Override
    public String toString() {
        return "Movies{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", actorName='" + actorName + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieEntity)) return false;
        MovieEntity movies = (MovieEntity) o;
        return Objects.equals(name, movies.name) && Objects.equals(actorName, movies.actorName) && Objects.equals(directorName, movies.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, actorName, directorName);
    }
}
