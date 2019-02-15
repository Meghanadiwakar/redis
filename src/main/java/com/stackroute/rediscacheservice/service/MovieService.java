package com.stackroute.rediscacheservice.service;

import com.stackroute.rediscacheservice.model.Movie;
import com.stackroute.rediscacheservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovies() {
        movieRepository.deleteAll();
    }

    public Iterator<Movie> getAllMovies() {
        return movieRepository.findAll().iterator();
    }
}
