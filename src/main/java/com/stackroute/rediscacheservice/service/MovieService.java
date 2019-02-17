package com.stackroute.rediscacheservice.service;

import com.stackroute.rediscacheservice.model.Movie;
import com.stackroute.rediscacheservice.repository.MovieMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private MovieMongoRepository movieMongoRepository;

    MovieService(MovieMongoRepository movieMongoRepository) {
        this.movieMongoRepository = movieMongoRepository;
    }

    public void addMovie(Movie movie) {
        movieMongoRepository.save(movie);
    }

    public void deleteMovies() {
        movieMongoRepository.deleteAll();
    }

    public List<Movie> getAllMovies() {
        return movieMongoRepository.findAll();
    }

    public Movie getById(int movieId) {
        return movieMongoRepository.findById(movieId).get();
    }
}
