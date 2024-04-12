package com.quad.Trivia.models;

import java.util.List;

public class Question {
    String question;
    List<String> answers;
    String encodedAnswer;

    public Question(String question, List<String> answers, String encodedAnswer){
        this.question = question;
        this.answers = answers;
        this.encodedAnswer = encodedAnswer;
    }

    public String getQuestion(){
        return question;
    }

    public List<String> getAnswers(){
        return answers;
    }

    public String getEncodedAnswer(){
        return encodedAnswer;
    }

}
