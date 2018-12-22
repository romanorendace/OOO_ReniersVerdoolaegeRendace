package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Romano Rendace
 */
public class MultipleChoiceQuestion extends Question  {

    private List<String> statements;

    public MultipleChoiceQuestion(String question, Category category, String feedback, List<String> statements) {
        super(question, category, feedback);
        setStatements(statements);
    }

    public MultipleChoiceQuestion(String question, Category category, String feedback) {
        super(question, category, feedback);
        this.statements = new ArrayList<>();
    }

    public void setStatements(List<String> statements) {
        this.statements = statements;
    }

    public List<String> getStatements() {
        return statements;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultipleChoiceQuestion)) return false;
        if (!super.equals(o)) return false;
        MultipleChoiceQuestion question = (MultipleChoiceQuestion) o;
        return Objects.equals(getStatements(), question.getStatements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStatements());
    }
}

