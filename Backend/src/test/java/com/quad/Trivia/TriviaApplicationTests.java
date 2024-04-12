package com.quad.Trivia;

import com.quad.Trivia.models.Question;
import com.quad.Trivia.services.QuestionService;
import com.quad.Trivia.utils.Cryptography;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class TriviaApplicationTests {

	@Autowired
	private QuestionService questionService;


	@Test
	public void VerifyQuestions(){
		String[] answers = new String[]{"Answer 1", "Answer 2"};
		Map<String, String> answerMap = new LinkedHashMap<>();
		for(int i=0; i< answers.length; i++){
            try {
                answerMap.put(Cryptography.encrypt(answers[i]), answers[i]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

		Boolean[] verifiedAnswers = questionService.VerifyQuestions(answerMap);
		Boolean[] expectedAnswer = new Boolean[answerMap.size()];
		Arrays.fill(expectedAnswer, Boolean.TRUE);
		assertArrayEquals(expectedAnswer, verifiedAnswers);
	}
}
