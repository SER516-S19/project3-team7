package model;

import java.util.List;

/**
 * Plain java class to map the Questions and Answers in Quiz
 *
 * @author : Jahnvi Rai
 * @version : 1.0
 * @since : 04/02/2019
 */

public class Question {

    private String title;
    private List<String> options;
    private String correctAnswer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", options=" + options +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
