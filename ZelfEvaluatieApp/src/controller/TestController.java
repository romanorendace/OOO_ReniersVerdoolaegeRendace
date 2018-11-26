package controller;

import model.ZelfEvaluatieService;
import view.panels.MessagePane;
import view.panels.TestPane;
import view.panels.ViewPane;

public class TestController implements Controller {

    private ZelfEvaluatieService service;

    private ViewPane testPane;
    private ViewPane messagePane;

    public TestController() {
    }

    public void setService(ZelfEvaluatieService service) {
        this.service = service;
    }

    public void setTestPane(ViewPane testPane) {
        this.testPane = testPane;
    }

    public void setMessagePane(ViewPane messagePane) {
        this.messagePane = messagePane;
    }

    @Override
    public void handleRequest(ViewPane viewPane) {
        
    }
}
