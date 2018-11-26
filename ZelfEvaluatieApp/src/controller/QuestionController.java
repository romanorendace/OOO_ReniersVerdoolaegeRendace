package controller;

import model.ZelfEvaluatieService;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.ViewPane;

public class QuestionController implements Controller{

    private ZelfEvaluatieService service;

    private ViewPane questionOverviewPane;
    private ViewPane questionDetailPane;

    public QuestionController() {
    }

    public void setService(ZelfEvaluatieService service) {
        this.service = service;
    }

    public void setQuestionOverviewPane(ViewPane questionOverviewPane) {
        this.questionOverviewPane = questionOverviewPane;
    }

    public void setQuestionDetailPane(ViewPane questionDetailPane) {
        this.questionDetailPane = questionDetailPane;
    }

    @Override
    public void handleRequest(ViewPane viewPane) {

    }
}
