package model;

import java.util.List;
import java.util.Objects;

public class MultipleChoiceQuestion extends Question  {

    private List<String> statements;

    public MultipleChoiceQuestion(String question, Category category, List<String> statements, String feedback) {
        super(question, category, feedback);
        this.statements = statements;
    }

    public List<String> getStatements() {
        return statements;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultipleChoiceQuestion)) return false;
        MultipleChoiceQuestion multipleChoiceQuestion1 = (MultipleChoiceQuestion) o;
        return Objects.equals(getQuestion(), multipleChoiceQuestion1.getQuestion()) &&
                Objects.equals(getCategory(), multipleChoiceQuestion1.getCategory());
    }
}

