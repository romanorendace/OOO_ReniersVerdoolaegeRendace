package controller.requestHandler;

import controller.QuestionController;
import model.Category;
import model.MultipleChoiceQuestion;
import model.Question;
import view.panels.QuestionDetailPane;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Romano Rendace
 */
public class EditQuestion extends RequestHandler {

    QuestionController controller;
    QuestionDetailPane questionDetailPane;

    @Override
    public void handleRequest() {
        controller = (QuestionController) getController();
        questionDetailPane = controller.getQuestionDetailPane();

        Question editedQuestion = getEditedQuestion();
        Question originalQuestion = controller.getOriginalQuestionToEdit();

        if (!originalQuestion.equals(editedQuestion)) {
            controller.getService().updateQuestion(originalQuestion, editedQuestion);
        }

        questionDetailPane.setSaveActionToSave();
        controller.getPane().getStage().close();
    }


    private Question getEditedQuestion() {
        String questionString = questionDetailPane.getQuestionFieldString();
        String categoryString = questionDetailPane.getCategoryFieldString();
        String feedbackString = questionDetailPane.getFeedbackField();
        Category category = controller.getService().getCategory(categoryString);
        List<String> statementsList = new ArrayList(controller.getStatements());

        Question question = new MultipleChoiceQuestion(questionString, category, feedbackString, statementsList);

        return question;

    }
}
