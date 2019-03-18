package com.stackroute.rediscacheservice.repository;

import com.stackroute.rediscacheservice.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

public interface QuestionRepository {

    void save(Question question);

    Map<String, Question> findAll();

    Question findById(String questionid);

    void update(Question question);

    void delete(String questionid);

}
