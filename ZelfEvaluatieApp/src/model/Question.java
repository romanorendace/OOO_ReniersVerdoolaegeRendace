package model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Question implements Comparable<Question> {

    private String question;
    private Category category;
    private List<String> statements;
    private String feedback;

    public Question(String question, Category category/*, List<String> statements*/, String feedback) {
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
        if (!(o instanceof Question)) return false;
        Question question1 = (Question) o;
        return Objects.equals(getQuestion(), question1.getQuestion()) &&
                Objects.equals(getCategory(), question1.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestion(), getCategory());
    }

    @Override
    public int compareTo(Question other) {
        return this.question.compareTo(other.getQuestion());
    }
}

