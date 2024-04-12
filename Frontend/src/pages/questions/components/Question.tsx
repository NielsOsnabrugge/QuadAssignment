import { Fragment, useState } from "react";
import Answer from "./Answer";
import parse from "html-react-parser";

interface Props {
  question: string;
  answers: string[];
  correctAnswer: boolean | null;
  onAnswerGiven: (question: string, answer: string) => void;
}

const Question = ({
  question,
  answers,
  correctAnswer,
  onAnswerGiven,
}: Props) => {
  const [selectedOption, setSelectedOption] = useState("");

  const onAnswerSelectedChange = (event: any) => {
    setSelectedOption(event.target.value);
    onAnswerGiven(question, event.target.value);
  };

  const answerList = answers.map((answer, index) => (
    <Fragment key={index}>
      <Answer
        answer={answer}
        isSelected={selectedOption == answer}
        correctAnswer={correctAnswer}
        onValueChange={onAnswerSelectedChange}
      />
      <br />
    </Fragment>
  ));

  return (
    <div>
      <h1>{parse(question)}</h1>
      {answerList}
    </div>
  );
};

export default Question;
