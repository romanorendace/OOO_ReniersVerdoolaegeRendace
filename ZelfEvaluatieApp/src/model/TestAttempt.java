package model;

import model.evaluationStrategy.EvaluationStrategy;
import model.evaluationStrategy.ScoreEvaluation;
import model.testStates.Completed;
import model.testStates.Ongoing;
import model.testStates.ReadyToStart;
import model.testStates.TestState;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestAttempt {

    /**
     * STORY 5
     * radio button in toggle group
     *
     * STORY 6
     * score in Map<Category, Integer>
     * strategy hoe score berekenen (metFoutCorrectie, zonderFoutCorrectie)
     */

    private Map<Question, Boolean> questionsAndIsCorrectlyAnswered;
    private Map<Category, Integer> scoreMap;
    private EvaluationStrategy evaluationStrategy;

    private TestState readyToStart;
    private TestState ongoing;
    private TestState completed;
    private TestState state;

    public TestAttempt() {
        this.questionsAndIsCorrectlyAnswered = new LinkedHashMap<>();
        this.scoreMap = new HashMap<>();
        this.evaluationStrategy = new ScoreEvaluation();

        this.readyToStart = new ReadyToStart();
        this.ongoing = new Ongoing();
        this.completed = new Completed();
        setState(readyToStart);
    }


    public void setState(TestState state) {
        this.state = state;
    }

    public void setEvaluationStrategy(EvaluationStrategy evaluationStrategy) {
        this.evaluationStrategy = evaluationStrategy;
    }

    public void setQuestionsAndIsCorrectlyAnswered(Map<Question, Boolean> questionsAndIsCorrectlyAnswered) {
        this.questionsAndIsCorrectlyAnswered = questionsAndIsCorrectlyAnswered;
    }

   /* public Question getNextQuestion() {
        return questionsAndIsCorrectlyAnswered.;
    }*/
}
