package model.testStates;

import model.TestAttempt;

public class Completed implements TestState {

    private TestAttempt testAttempt;

    public Completed(TestAttempt testAttempt) {
        this.testAttempt = testAttempt;
    }

    @Override
    public void readyToStart() {
        // DO NOTHING
    }

    @Override
    public void ongoing() {
        // DO NOTHING
    }

    @Override
    public void completed() {



        testAttempt.setState(testAttempt.getReadyToStart());
    }
}
