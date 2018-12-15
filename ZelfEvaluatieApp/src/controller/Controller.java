package controller;

import controller.requestHandler.RequestHandler;
import controller.requestHandler.RequestHandlerFactory;
import model.ZelfEvaluatieService;

public abstract class Controller {

    private ZelfEvaluatieService service;
    private RequestHandlerFactory factory;


    public Controller() {
        this.service = new ZelfEvaluatieService();
        this.factory = new RequestHandlerFactory();
    }

    public void handleRequest(String request) {
        RequestHandler handler = factory.getHandler(request, getController());
        handler.handleRequest();
    }

    protected abstract Controller getController();

    public ZelfEvaluatieService getService() {
        return service;
    }

    public void setService(ZelfEvaluatieService service) {
        this.service = service;
    }
}
