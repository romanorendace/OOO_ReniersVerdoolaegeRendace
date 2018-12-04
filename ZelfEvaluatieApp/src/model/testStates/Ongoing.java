package model.testStates;

import model.TestAttempt;

public class Ongoing implements TestState {

    private TestAttempt testAttempt;

    public Ongoing(TestAttempt testAttempt) {
        this.testAttempt = testAttempt;
    }

    @Override
    public void readyToStart() {
        // DO NOTHING
    }

    @Override
    public void ongoing() {
        while (hasNextQuestion()) {



        }
        testAttempt.setState(testAttempt.getCompleted());
    }

    @Override
    public void completed() {
        // DO NOTHING
    }

    private boolean hasNextQuestion() {

        //TODO
        return false;
    }
}
