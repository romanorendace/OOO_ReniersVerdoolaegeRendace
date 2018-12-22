package controller.requestHandler;

import controller.QuestionController;
import javafx.collections.FXCollections;
import model.MultipleChoiceQuestion;
import model.Question;
import view.panels.CategoryDetailPane;
import view.panels.QuestionDetailPane;

import java.util.List;

/**
 * @author Romano Rendace
 */
public class ShowEditQuestionDetailPane extends RequestHandler {

    QuestionController controller;
    QuestionDetailPane questionDetailPane;
    MultipleChoiceQuestion question;

    @Override
    public void handleRequest() {
        controller = (QuestionController) getController();
        questionDetailPane = controller.getQuestionDetailPane();
        question = (MultipleChoiceQuestion) controller.getQuestionOverviewPane().getSelectedQuestion();

        List<String> statement = question.getStatements();
        controller.setStatements(statement);
        controller.updateStatementsListOverview();

        questionDetailPane.setQuestionField(question.getQuestion());
        questionDetailPane.setCategoryField(question.getCategory().getTitle());
        questionDetailPane.setFeedbackField(question.getFeedback());

        questionDetailPane.setSaveActionForEdit();

        controller.setOriginalQuestionToEdit(question);
        controller.getPane().addGridPaneToBorderPane(controller.getQuestionDetailPane());
        controller.getPane().getStage().show();
    }
}
