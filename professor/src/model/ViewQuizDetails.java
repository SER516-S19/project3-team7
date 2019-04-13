package model;

import java.util.List;

public class ViewQuizDetails {

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    List<Questions> questions;

    @Override
    public String toString() {
        return "Quiz{" +
                "questions=" + questions +
                '}';
    }
}
