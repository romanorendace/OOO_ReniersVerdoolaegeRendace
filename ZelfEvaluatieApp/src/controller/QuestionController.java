package controller;

import view.panels.Pane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import java.util.ArrayList;
import java.util.List;

public class QuestionController extends Controller {

    private QuestionOverviewPane questionOverviewPane;
    private QuestionDetailPane questionDetailPane;
    private Pane pane;

    private List<String> statements;


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

}
