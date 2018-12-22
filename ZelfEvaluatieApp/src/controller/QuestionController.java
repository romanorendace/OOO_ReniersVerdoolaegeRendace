package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Question;
import view.panels.Pane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Romano Rendace
 */
public class QuestionController extends Controller {

    private QuestionOverviewPane questionOverviewPane;
    private QuestionDetailPane questionDetailPane;
    private Pane pane;

    private List<String> statements;
    private Question originalQuestionToEdit;


    public QuestionController() {
        statements = new ArrayList<>();
        pane = new Pane();
    }

    // GETTERS & SETTERS
    public void setQuestionOverviewPane(QuestionOverviewPane questionOverviewPane) {
        this.questionOverviewPane = questionOverviewPane;
    }
    public QuestionOverviewPane getQuestionOverviewPane() {
        return questionOverviewPane;
    }

    public void setQuestionDetailPane(QuestionDetailPane questionDetailPane) {
        this.questionDetailPane = questionDetailPane;
    }
    public QuestionDetailPane getQuestionDetailPane() {
        return questionDetailPane;
    }

    public Pane getPane() {
        return pane;
    }

    public List<String> getStatements() {
        return statements;
    }
    public void setStatements(List<String> statements) {
        this.statements = statements;
    }

    @Override
    protected Controller getController() {
        return this;
    }

    public void setOriginalQuestionToEdit(Question originalQuestionToEdit) {
        this.originalQuestionToEdit = originalQuestionToEdit;
    }
    public Question getOriginalQuestionToEdit() {
        return originalQuestionToEdit;
    }

    // AUX METHODS
    public void updateStatementsListOverview() {
        ObservableList<String> statementsObservableList = FXCollections.observableList(statements);
        questionDetailPane.updateStatementsInView(statementsObservableList);
    }
}
