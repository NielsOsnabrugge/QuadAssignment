import parse from "html-react-parser";

interface Props {
  answer: string;
  isSelected: boolean;
  correctAnswer: boolean | null;
  onValueChange: (event: any) => void;
}

const Answer = ({
  answer,
  isSelected,
  correctAnswer,
  onValueChange,
}: Props) => {
  const getClassName = () => {
    if (!isSelected || correctAnswer == null) return "";

    if (correctAnswer == true) {
      return "form-control is-valid";
    } else if (correctAnswer == false) {
      return "form-control is-invalid";
    }
  };

  return (
    <>
      <label>
        <input
          type="radio"
          value={answer}
          className={getClassName()}
          checked={isSelected}
          disabled={correctAnswer !== null}
          onChange={onValueChange}
        />
        {parse(answer)}
      </label>
    </>
  );
};

export default Answer;
