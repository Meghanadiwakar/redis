package com.stackroute.rediscacheservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "movies")
@Data
public class Movie implements Serializable {
    @Id
    private int movieId;
    private String movieTitle;
}
