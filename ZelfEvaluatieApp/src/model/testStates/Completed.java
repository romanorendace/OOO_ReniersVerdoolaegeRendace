package model.testStates;

import model.TestAttempt;

/**
 * @author Romano Rendace
 */
public class Completed extends TestState {


    public Completed(TestAttempt testAttempt) {
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
        getTestAttempt().setState(getTestAttempt().getReadyToStart());
    }
}
