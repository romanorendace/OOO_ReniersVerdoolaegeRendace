package model;

import java.util.Objects;

/**
 * @author Romano Rendace
 */
public abstract class Question implements Comparable<Question> {

    private String question;
    private Category category;
    private String feedback;
    private boolean isCorrect;

    public Question(String question, Category category, String feedback) {
        this.question = question;
        this.category = category;
        this.feedback = feedback;
        this.isCorrect = false;
    }

    public String getQuestion() {
        return question;
    }

    public Category getCategory() {
        return category;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestion(), getCategory());
    }

    @Override
    public int compareTo(Question other) {
        return this.question.compareTo(other.getQuestion());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question1 = (Question) o;
        return Objects.equals(getQuestion(), question1.getQuestion()) &&
                Objects.equals(getCategory(), question1.getCategory()) &&
                Objects.equals(getFeedback(), question1.getFeedback());
    }
}
