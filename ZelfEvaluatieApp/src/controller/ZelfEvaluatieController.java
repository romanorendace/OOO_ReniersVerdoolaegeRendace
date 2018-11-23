package controller;

import model.ZelfEvaluatieService;
import view.panels.*;

public class ZelfEvaluatieController {

    private ZelfEvaluatieService facade;
    private CategoryOverviewPane categoryOverviewPane;
    private CategoryDetailPane categoryDetailPane;
    private QuestionOverviewPane questionOverviewPane;
    private QuestionDetailPane questionDetailPane;
    private MessagePane messagePane;
    private TestPane testPane;

    public ZelfEvaluatieController() {
    }

    public void setFacade(ZelfEvaluatieService facade) {
        this.facade = facade;
    }

    public void setTestPane(TestPane testPane) {
        this.testPane = testPane;
    }

    public void setCategoryOverviewPane(CategoryOverviewPane categoryOverviewPane) {
        this.categoryOverviewPane = categoryOverviewPane;
    }

    public void setCategoryDetailPane(CategoryDetailPane categoryDetailPane) {
        this.categoryDetailPane = categoryDetailPane;
    }

    public void setQuestionOverviewPane(QuestionOverviewPane questionOverviewPane) {
        this.questionOverviewPane = questionOverviewPane;
    }

    public void setQuestionDetailPane(QuestionDetailPane questionDetailPane) {
        this.questionDetailPane = questionDetailPane;
    }

    public void setMessagePane(MessagePane messagePane) {
        this.messagePane = messagePane;
    }
}
