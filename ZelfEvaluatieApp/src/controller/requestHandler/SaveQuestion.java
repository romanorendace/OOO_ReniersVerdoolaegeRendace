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
public class SaveQuestion extends RequestHandler {

    QuestionController controller;

    @Override
    public void handleRequest() {
        controller = (QuestionController) getController();
        QuestionDetailPane questionDetailPane = controller.getQuestionDetailPane();

        String questionString = questionDetailPane.getQuestionFieldString();
        String categoryString = questionDetailPane.getCategoryFieldString();
        String feedbackString = questionDetailPane.getFeedbackField();
        Category category = controller.getService().getCategory(categoryString);
        List<String> statementsList = new ArrayList(controller.getStatements());

        Question question = new MultipleChoiceQuestion(questionString, category, feedbackString, statementsList);
        controller.getService().saveNewQuestion(question);

        clearInputFields();
        controller.getPane().getStage().close();
    }

    public void clearInputFields() {
        controller.getQuestionDetailPane().clearInputFields();
    }
}
