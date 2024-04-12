import { useRef } from "react";
import { useQuery } from "@tanstack/react-query";
import { fetchQuestionAnswers, validateAnswers } from "../../api/QuestionsAPI";
import Question from "./components/Question";

function QuestionList() {
  const answers = useRef<Map<string, string>>(new Map());
  const { data: questionAnswers, isLoading } = useQuery({
    queryKey: ["questionanswers"],
    queryFn: () => {
      answers.current = new Map();
      refetch();
      return fetchQuestionAnswers();
    },
  });

  const { data: correctAnswers, refetch } = useQuery({
    queryKey: ["correct_questionanswers"],
    queryFn: () => {
      if (answers.current.size !== questionAnswers?.length) return null;
      return validateAnswers(answers.current);
    },
    enabled: false,
  });

  const onAnswerGiven = (question: string, answer: string) => {
    questionAnswers?.forEach((questionAnswer) => {
      if (questionAnswer.question == question) {
        answers.current.set(questionAnswer.encodedAnswer, answer);
      }
    });
  };

  const formSubmit = () => {
    if (answers.current.size === questionAnswers?.length) {
      refetch();
    }
  };

  if (isLoading) {
    return <div>Loading Questions...</div>;
  }

  if (questionAnswers?.length == 0) {
    return (
      <div>No questions could be found. Please visit the page again later.</div>
    );
  }

  return (
    <div>
      {questionAnswers?.map((questionAnswer, index) => (
        <Question
          key={index}
          question={questionAnswer.question}
          answers={questionAnswer.answers}
          correctAnswer={
            correctAnswers == undefined ? null : correctAnswers[index]
          }
          onAnswerGiven={onAnswerGiven}
        ></Question>
      ))}
      <button type="submit" className="btn btn-primary" onClick={formSubmit}>
        Submit
      </button>
    </div>
  );
}

export default QuestionList;
