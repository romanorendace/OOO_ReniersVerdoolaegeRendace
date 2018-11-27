package model;

import java.util.List;
import java.util.Objects;

public class MultipleChoiceQuestion extends Question  {

    private String question;
    private Category category;
    private List<String> statements;
    private String feedback;

    public MultipleChoiceQuestion(String question, Category category, List<String> statements, String feedback) {
        setQuestion(question);
        setCategory(category);
        setStatements(statements);
        setFeedback(feedback);
    }


    public String getQuestion() {
        return question;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getStatements() {
        return statements;
    }

    public String getFeedback() {
        return feedback;
    }

    private void setQuestion(String question) {
        this.question = question;
    }

    private void setCategory(Category category) {
        this.category = category;
    }

    private void setStatements(List<String> statements) {
        this.statements = statements;
    }

    private void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MultipleChoiceQuestion)) return false;
        MultipleChoiceQuestion multipleChoiceQuestion1 = (MultipleChoiceQuestion) o;
        return Objects.equals(getQuestion(), multipleChoiceQuestion1.getQuestion()) &&
                Objects.equals(getCategory(), multipleChoiceQuestion1.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestion(), getCategory());
    }

    @Override
    public int compareTo(MultipleChoiceQuestion other) {
        return this.question.compareTo(other.getQuestion());
    }
}

