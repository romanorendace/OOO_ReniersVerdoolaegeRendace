package model.testStates;

import model.TestAttempt;

public class ReadyToStart implements TestState {

    private TestAttempt testAttempt;

    public ReadyToStart(TestAttempt testAttempt) {
        this.testAttempt = testAttempt;
    }

    @Override
    public void readyToStart() {

    }

    @Override
    public void ongoing() {
        // DO NOTHING
    }

    @Override
    public void completed() {
        // DO NOTHING
    }

}
