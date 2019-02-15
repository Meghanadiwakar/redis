package com.stackroute.rediscacheservice.controller;

import com.stackroute.rediscacheservice.model.Movie;
import com.stackroute.rediscacheservice.service.MovieService;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class HomeController {
    private MovieService movieService;

    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getAllMovies() {
        ResponseEntity<List<Movie>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(IteratorUtils.toList(movieService.getAllMovies()), HttpStatus.OK);
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

}
