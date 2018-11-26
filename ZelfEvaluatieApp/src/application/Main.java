package application;

import controller.CategoryController;
import controller.QuestionController;
import controller.TestController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ZelfEvaluatieService;
import view.panels.AssesMainPane;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.MessagePane;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.TestPane;

public class Main extends Application {

    private QuestionOverviewPane questionOverviewPane;
    private QuestionDetailPane questionDetailPane;

    private CategoryOverviewPane categoryOverviewPanel;
    private CategoryDetailPane categoryDetailPanel;

    private TestPane testPane;
    private MessagePane messagePane;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;

    private CategoryController categoryController;
    private QuestionController questionController;
    private TestController testController;

    private ZelfEvaluatieService zelfEvaluatieService;


    @Override
    public void start(Stage primaryStage) {

        try {
            initializeViewPaneObjects();
            initializeControllerObjects();
            inititalizeModelObjects();

            setModelViewControllerReferences();
            registerObserversInMVC();




            setupView(primaryStage);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initializeViewPaneObjects() {
        questionOverviewPane = new QuestionOverviewPane();
        questionDetailPane = new QuestionDetailPane();

        categoryOverviewPanel = new CategoryOverviewPane();
        categoryDetailPanel = new CategoryDetailPane();

        testPane = new TestPane();
        messagePane = new MessagePane();
    }
    private void initializeControllerObjects() {
          categoryController = new CategoryController();
          questionController = new QuestionController();
          testController = new TestController();
    }
    private void inititalizeModelObjects() {
        zelfEvaluatieService = new ZelfEvaluatieService();
    }

    private void setModelViewControllerReferences() {
        setMVCReferencesForViewObject();
        setMVCReferencesForControllerObjects();
        setMVCReferencesForModelObjects();
    }
    private void setMVCReferencesForViewObject() {
        categoryOverviewPanel.setService(zelfEvaluatieService);
        categoryOverviewPanel.setContoller(categoryController);

        categoryDetailPanel.setService(zelfEvaluatieService);
        categoryDetailPanel.setContoller(categoryController);

        questionOverviewPane.setService(zelfEvaluatieService);
        questionOverviewPane.setController(questionController);

        questionDetailPane.setService(zelfEvaluatieService);
        questionDetailPane.setController(questionController);

        testPane.setService(zelfEvaluatieService);
        testPane.setController(testController);

        messagePane.setService(zelfEvaluatieService);
        messagePane.setController(testController);
    }
    private void setMVCReferencesForControllerObjects() {
        categoryController.setService(zelfEvaluatieService);
        categoryController.setCategoryOverviewPane(categoryOverviewPanel);
        categoryController.setCategoryDetailPane(categoryDetailPanel);

        questionController.setService(zelfEvaluatieService);
        questionController.setQuestionOverviewPane(questionOverviewPane);
        questionController.setQuestionDetailPane(questionDetailPane);

        testController.setService(zelfEvaluatieService);
        testController.setTestPane(testPane);
        testController.setMessagePane(messagePane);
    }
    private void setMVCReferencesForModelObjects() {
        zelfEvaluatieService.setCategoryController(categoryController);
        zelfEvaluatieService.setQuestionController(questionController);
        zelfEvaluatieService.setTestController(testController);
    }

    private void registerObserversInMVC() {
        registerObserversinView();
    }
    private void registerObserversinView() {
        zelfEvaluatieService.registerObserver(categoryOverviewPanel);
        zelfEvaluatieService.registerObserver(categoryDetailPanel);

        zelfEvaluatieService.registerObserver(questionOverviewPane);
        zelfEvaluatieService.registerObserver(questionDetailPane);
    }

    private void setupView(Stage primaryStage) {
        root = new Group();
        scene = new Scene(root, 750, 400);

        borderPane = new AssesMainPane(messagePane, categoryOverviewPanel, questionOverviewPane);
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
    }


}
