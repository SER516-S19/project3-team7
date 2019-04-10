package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Plain java class to map the Quiz created by professor
 */

public class Quiz {
    private List<Questions> questions = new ArrayList<>();

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }
}
