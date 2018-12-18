package model;

import model.evaluationStrategy.EvaluationStrategy;
import model.evaluationStrategy.ScoreEvaluation;
import model.testStates.Completed;
import model.testStates.Ongoing;
import model.testStates.ReadyToStart;
import model.testStates.TestState;

import java.util.*;

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
    private Map<Category, Integer> scorePerCategory;
    private EvaluationStrategy evaluationStrategy;

    private TestState readyToStart;
    private TestState ongoing;
    private TestState completed;
    private TestState state;

    private Iterator iterator;


    public TestAttempt() {
        this.questionsAndIsCorrectlyAnswered = new LinkedHashMap<>();
        this.scorePerCategory = new HashMap<>();
        setEvaluationStrategy(new ScoreEvaluation());

        this.readyToStart = new ReadyToStart(this);
        this.ongoing = new Ongoing(this);
        this.completed = new Completed(this);
        setState(readyToStart);
    }

    public TestState getReadyToStart() {
        return readyToStart;
    }

    public TestState getOngoing() {
        return ongoing;
    }

    public TestState getCompleted() {
        return completed;
    }

    public Map<Question, Boolean> getQuestionsAndIsCorrectlyAnswered() {
        return questionsAndIsCorrectlyAnswered;
    }

    public Map<Category, Integer> getScorePerCategory() {
        return scorePerCategory;
    }

    public void setState(TestState state) {
        this.state = state;
    }

    public void setEvaluationStrategy(EvaluationStrategy evaluationStrategy) {
        this.evaluationStrategy = evaluationStrategy;
    }

    public void setQuestionsAndIsCorrectlyAnswered(Map<Question, Boolean> questionsAndIsCorrectlyAnswered) {
        this.questionsAndIsCorrectlyAnswered = questionsAndIsCorrectlyAnswered;
        this.iterator = questionsAndIsCorrectlyAnswered.keySet().iterator();
    }

    public Question getNextQuestion() {
        System.out.println("lengte " + questionsAndIsCorrectlyAnswered.size());
        Question question = null;
        if(iterator.hasNext()) {
            question = (Question)iterator.next();
        }
            return question;
    }

    public void setVerification(Question question, boolean verification) {
        questionsAndIsCorrectlyAnswered.replace(question, verification);
    }
}
