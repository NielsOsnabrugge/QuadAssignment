package com.quad.Trivia.controllers;
import com.quad.Trivia.models.Question;
import com.quad.Trivia.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/questions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Question[] GetQuestions() {
        return this.questionService.GetQuestions(5);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/checkanswers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean[] CheckAnswers(@RequestBody Map<String, String> encodedAnswerAnswer) {
        return this.questionService.VerifyQuestions(encodedAnswerAnswer);
    }

}
