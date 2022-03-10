package com.aprendendospring.controllers;

import com.aprendendospring.models.Movie;
import com.aprendendospring.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class AprendendospringController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/")
    public List<Movie> getList() {
      return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable("id") long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) return movie.get();
        return null;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public void updateMovie(@RequestBody Movie movie, @PathVariable("id") long id) {
        Optional<Movie> movieFind = Optional.ofNullable(movieRepository.findById(id).orElse(null));
        if (movieFind != null) movieRepository.save(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") long id) {
        movieRepository.deleteById(id);
    }

}
