package com.stackroute.rediscacheservice.repository;


import com.stackroute.rediscacheservice.model.Question;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository{


    private RedisTemplate<String, Question> redisTemplate;

    private HashOperations hashOperations;


    public QuestionRepositoryImpl(RedisTemplate<String, Question> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Question question) {

        hashOperations.put("QUESTION",question.getQuestionid(),question);


    }

    @Override
    public Map<String, Question> findAll() {
        return hashOperations.entries("QUESTION");
    }

    @Override
    public Question findById(String questionid) {
        return (Question) hashOperations.get("QUESTION",questionid);
    }

    @Override
    public void update(Question question)
    {
        save(question);
    }

    @Override
    public void delete(String questionid) {
     hashOperations.delete("QUESTION",questionid);

    }
}
