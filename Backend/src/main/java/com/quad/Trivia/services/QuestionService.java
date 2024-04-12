package com.quad.Trivia.services;

import com.quad.Trivia.models.Question;
import com.quad.Trivia.utils.Cryptography;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuestionService {

    public Question[] GetQuestions(int amount) {
        RestTemplate restTemplate = new RestTemplate();

        Question[] questions = null;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("https://opentdb.com/api.php?amount=" + amount, String.class);

            JSONObject responseJson = new JSONObject(response.getBody());
            questions = parseJsonToQuestions(responseJson);
        }
        catch (HttpClientErrorException e){
            // Likely 429 error to many fetches occurring, could retry x times but for now we just return an empty array
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return questions;
    }

    public Boolean[] VerifyQuestions(Map<String, String> answers){
        ArrayList<Boolean> correctAnswers = new ArrayList<Boolean>();

        Iterator<String> iterator = answers.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            try {
                correctAnswers.add(Cryptography.decrypt(key).equals(answers.get(key)));
            } catch (Exception e) {
                correctAnswers.add(false);
            }
        }

        return correctAnswers.toArray(new Boolean[correctAnswers.size()]);
    }

    // Parses Open Trivia JSON questions to Question instances
    private Question[] parseJsonToQuestions(JSONObject jsonObject) throws Exception{
        JSONArray resultSet = (JSONArray) jsonObject.get("results");
        Question[] questions = new Question[resultSet.length()];
        for (int i=0; i < resultSet.length(); i++) {
            JSONObject jsonQuestion = resultSet.getJSONObject(i);
            JSONArray invalidAnswersJson = jsonQuestion.getJSONArray("incorrect_answers");

            List<String> allAnswers = new ArrayList<>();
            for(int j=0; j<invalidAnswersJson.length(); j++){
                allAnswers.add(invalidAnswersJson.getString(j));
            }

            String validAnswer = jsonQuestion.getString("correct_answer");
            allAnswers.add(validAnswer);
            Collections.shuffle(allAnswers);

            questions[i] = new Question(jsonQuestion.getString("question"), allAnswers, Cryptography.encrypt(validAnswer));
        }

        return questions;
    }
}
