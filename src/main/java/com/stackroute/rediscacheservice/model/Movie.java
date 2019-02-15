package com.stackroute.rediscacheservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "movie")
@Data
public class Movie {
    @Id
    private int movieId;
    private String movieTitle;
}
