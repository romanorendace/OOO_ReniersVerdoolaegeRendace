package model.testStates;

import model.TestAttempt;

/**
 * @author Romano Rendace
 */
public abstract class TestState {

    private TestAttempt testAttempt;

    public abstract void startTest();
    public abstract void showNextQuestion();
    public abstract void showFinalEvaluation();

    public TestState(TestAttempt testAttempt) {
        this.testAttempt = testAttempt;
    }

    public TestAttempt getTestAttempt() {
        return testAttempt;
    }

    public void setTestAttempt(TestAttempt testAttempt) {
        this.testAttempt = testAttempt;
    }
}
