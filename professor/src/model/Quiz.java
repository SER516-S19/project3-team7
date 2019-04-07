package model;


import java.util.ArrayList;
import java.util.List;

public class Quiz {
    public List<Questions> questions = new ArrayList<>();

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }
}
