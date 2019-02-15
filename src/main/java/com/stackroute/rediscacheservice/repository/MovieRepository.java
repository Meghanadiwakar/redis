package com.stackroute.rediscacheservice.repository;

import com.stackroute.rediscacheservice.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository  extends CrudRepository<Movie,Integer> {
}
