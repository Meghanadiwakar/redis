package com.stackroute.rediscacheservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


public class Question implements Serializable {

    private String questionid;
    private String questionstring;

    public Question(String questionid, String questionstring) {
        this.questionid = questionid;
        this.questionstring = questionstring;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getQuestionstring() {
        return questionstring;
    }

    public void setQuestionstring(String questionstring) {
        this.questionstring = questionstring;
    }
}
