package com.stackroute.rediscacheservice.controller;

import com.stackroute.rediscacheservice.model.Movie;
import com.stackroute.rediscacheservice.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class HomeController {
    private MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getAllMovies() {
        ResponseEntity<List<Movie>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @PostMapping("/movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        ResponseEntity<String> responseEntity;
        try {
            movieService.addMovie(movie);
            responseEntity = new ResponseEntity<>("Saved Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Error occurred", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @DeleteMapping("/movie")
    public ResponseEntity<String> deleteMovie() {
        ResponseEntity<String> responseEntity;
        try {
            movieService.deleteMovies();
            responseEntity = new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("Error occurred", HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("/find/{movieId}")
    @Cacheable(value = "moviecache")
    @ResponseBody
    public Movie getById(@PathVariable int movieId) {
        Movie movie;

        try {
            movie = movieService.getById(movieId);

        } catch (Exception e) {
            e.printStackTrace();
            movie = null;
        }
        return movie;
    }

    @Scheduled(cron = "0 * * ? * *")
    @CacheEvict(value = "moviecache", allEntries = true)
    public void clearRedisCache() {
        log.info("Clearing Redis Cache after 1 minute");
    }
}
