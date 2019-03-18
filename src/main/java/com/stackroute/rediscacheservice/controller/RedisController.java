package com.stackroute.rediscacheservice.controller;

import com.stackroute.rediscacheservice.model.Question;
import com.stackroute.rediscacheservice.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/question")
@Slf4j
public class RedisController {
    private QuestionRepository questionRepository;

    public RedisController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @PostMapping("/add")
    public void add(@RequestBody Question question) {
        questionRepository.save(question);
    }


    @GetMapping("/all")
    public Map<String, Question> all() {
        return questionRepository.findAll();
    }


    @GetMapping("/find/{questionid}")
    @Cacheable(value = "questioncache")
    @ResponseBody
    public Question getById(@PathVariable String questionid) {
        System.out.println("inside intellij");
        Question question;

        try {
            question = questionRepository.findById(questionid);

        } catch (Exception e) {
            e.printStackTrace();
            question = null;
        }
        return question;
    }



    @Scheduled(cron = "0 */2 * ? * *")
    @CacheEvict(value = "questioncache", allEntries = true)
    public void clearRedisCache() {
        log.info("Clearing Redis Cache after 2 minute");
    }
}

