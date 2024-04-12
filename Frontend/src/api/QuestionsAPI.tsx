import { QuestionAnswers } from "../entities/QuestionAnswers";
import axios from "axios";

const API_BASE_URL = "http://localhost:8080"; // Replace with your actual API base URL

export const fetchQuestionAnswers = async (): Promise<QuestionAnswers[]> => {
  try {
    const response = await axios.get(`${API_BASE_URL}/questions`, {
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    });
    if (response.data == "") return [];
    return response.data;
  } catch (error) {
    console.error("Error fetching users:", error);
  }
  return [];
};

export const validateAnswers = async (
  answers: Map<string, string>
): Promise<boolean[]> => {
  try {
    const response = await axios.post(
      `${API_BASE_URL}/checkanswers`,
      Object.fromEntries(answers)
    );

    return response.data;
  } catch (error) {
    console.error("Error fetching users:", error);

    throw error;
  }
};
