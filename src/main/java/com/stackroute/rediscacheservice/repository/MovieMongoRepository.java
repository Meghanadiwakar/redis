package com.stackroute.rediscacheservice.repository;

import com.stackroute.rediscacheservice.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieMongoRepository extends MongoRepository<Movie, Integer> {
}
