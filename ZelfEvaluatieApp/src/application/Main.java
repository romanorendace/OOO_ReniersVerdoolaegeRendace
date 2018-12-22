package application;

import controller.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ZelfEvaluatieService;
import view.panels.*;

/**
 * @author Romano Rendace
 */
public class Main extends Application {

    private QuestionOverviewPane questionOverviewPane;
    private QuestionDetailPane questionDetailPane;

    private CategoryOverviewPane categoryOverviewPanel;
    private CategoryDetailPane categoryDetailPanel;

    private OptionsPane optionsPane;

    private TestPane testPane;
    private MessagePane messagePane;

    private FeedbackPane feedbackPane;
    private ResultPane resultPane;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;

    private CategoryController categoryController;
    private QuestionController questionController;
    private TestController testController;
    private OptionsController optionsController;

    private ZelfEvaluatieService zelfEvaluatieService;


    @Override
    public void start(Stage primaryStage) {

        try {
            initializeModelObjects();
            initializeControllerObjects();
            initializeViewPaneObjects();

            setModelViewControllerReferences();
            registerObserversInMVC();

            loadDataFromStorageIntoLocalMemory();
            firstNotificationOfObservers();




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

        feedbackPane = new FeedbackPane();
        resultPane = new ResultPane();

        optionsPane = new OptionsPane();
    }
    private void initializeControllerObjects() {
          categoryController = new CategoryController();
          questionController = new QuestionController();
          testController = new TestController();
          optionsController = new OptionsController();
    }
    private void initializeModelObjects() {
        zelfEvaluatieService = new ZelfEvaluatieService();
        zelfEvaluatieService.loadDataFromStorageIntoLocalMemory();
    }

    private void setModelViewControllerReferences() {
        setMVCReferencesForViewObject();
        setMVCReferencesForControllerObjects();
        setMVCReferencesForModelObjects();
    }
    private void setMVCReferencesForViewObject() {
        categoryOverviewPanel.setController(categoryController);
        categoryDetailPanel.setController(categoryController);

        questionOverviewPane.setController(questionController);
        questionDetailPane.setController(questionController);

        testPane.setController(testController);
        messagePane.setController(testController);

        feedbackPane.setController(testController);
        resultPane.setController(testController);

        optionsPane.setController(optionsController);
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
        testController.setFeedbackPane(feedbackPane);
        testController.setResultPane(resultPane);

        optionsController.setService(zelfEvaluatieService);
        optionsController.setOptionsPane(optionsPane);
    }
    private void setMVCReferencesForModelObjects() {
        zelfEvaluatieService.setCategoryController(categoryController);
        zelfEvaluatieService.setQuestionController(questionController);
        zelfEvaluatieService.setTestController(testController);
        zelfEvaluatieService.setOptionsController(optionsController);
    }

    private void registerObserversInMVC() {
        registerObserversInView();
    }
    private void registerObserversInView() {
        zelfEvaluatieService.getCategoryDB().registerObserver(categoryOverviewPanel);
        zelfEvaluatieService.getCategoryDB().registerObserver(categoryDetailPanel);
        zelfEvaluatieService.getCategoryDB().registerObserver(questionDetailPane);

        zelfEvaluatieService.getQuestionDB().registerObserver(questionOverviewPane);
        zelfEvaluatieService.getQuestionDB().registerObserver(questionDetailPane);
    }

    private void firstNotificationOfObservers() {
        zelfEvaluatieService.notifyObservers(null);
    }

    private void loadDataFromStorageIntoLocalMemory() {
        zelfEvaluatieService.loadDataFromStorageIntoLocalMemory();
    }

    private void setupView(Stage primaryStage) {
        root = new Group();
        scene = new Scene(root, 750, 400);

        borderPane = new AssesMainPane(messagePane, categoryOverviewPanel, questionOverviewPane, optionsPane);
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
    }


}
