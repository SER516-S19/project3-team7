package model;


public class Quiz {
    public String quizName;
    public String quizInstruction;
    public String quizType;
    public String assignmentGroup;
    public int shuffleAnswer;

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizInstruction() {
        return quizInstruction;
    }

    public void setQuizInstruction(String quizInstruction) {
        this.quizInstruction = quizInstruction;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public String getAssignmentGroup() {
        return assignmentGroup;
    }

    public void setAssignmentGroup(String assignmentGroup) {
        this.assignmentGroup = assignmentGroup;
    }

    public int getShuffleAnswer() {
        return shuffleAnswer;
    }

    public void setShuffleAnswer(int shuffleAnswer) {
        this.shuffleAnswer = shuffleAnswer;
    }
}
