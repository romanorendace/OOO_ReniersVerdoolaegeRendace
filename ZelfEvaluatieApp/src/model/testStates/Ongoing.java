package model.testStates;

import model.TestAttempt;

/**
 * @author Romano Rendace
 */
public class Ongoing extends TestState {

    public Ongoing(TestAttempt testAttempt) {
        super(testAttempt);
    }


    @Override
    public void startTest() {

    }

    @Override
    public void showNextQuestion() {
        while(hasNextQuestion()) {



            getTestAttempt().setState(getTestAttempt().getCompleted());
        }
    }

    @Override
    public void showFinalEvaluation() {

    }

    private boolean hasNextQuestion() {

        //TODO
        return false;
    }
}
