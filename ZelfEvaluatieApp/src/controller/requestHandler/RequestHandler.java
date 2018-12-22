package controller.requestHandler;

import controller.Controller;

/**
 * @author Romano Rendace
 */
public abstract class RequestHandler {
    
    private Controller controller;
    
    public abstract void handleRequest();

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
