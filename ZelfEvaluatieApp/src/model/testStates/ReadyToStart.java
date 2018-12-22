package model.testStates;

import model.TestAttempt;

/**
 * @author Romano Rendace
 */
public class ReadyToStart extends TestState {


    public ReadyToStart(TestAttempt testAttempt) {
        super(testAttempt);
    }


    @Override
    public void startTest() {

    }

    @Override
    public void showNextQuestion() {

    }

    @Override
    public void showFinalEvaluation() {

    }
}
