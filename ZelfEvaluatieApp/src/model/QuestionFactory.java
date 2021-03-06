package model;

/**
 * @author Romano Rendace
 */
public class QuestionFactory {

    public Question createQuestion(String questionType, String question, Category category, String feedback) {
        Question newQuestion = null;
        switch (questionType) {
            case "MultipleChoiceQuestion" :
                newQuestion = new MultipleChoiceQuestion(question, category, feedback);
        }
        return newQuestion;
    }
}
