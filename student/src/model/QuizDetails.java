package model;

import java.util.List;

/**
 * Plain java class for mapping the Quiz.json files
 *
 * @author : Jahnvi Rai
 * @version : 1.0
 * @since : 04/02/2019
 */

public class QuizDetails {

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    List<Question> questions;

    @Override
    public String toString() {
        return "Quiz{" +
                "questions=" + questions +
                '}';
    }
}
