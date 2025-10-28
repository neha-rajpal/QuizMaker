package model;
import java.io.Serializable;

public class Question implements Serializable {
    private String questionText;
    private String[] options;
    private int correctAnswer;
    private int marks;

    public Question(String questionText, String[] options, int correctAnswer, int marks) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.marks = marks;
    }

    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public int getCorrectAnswer() { return correctAnswer; }
    public int getMarks() { return marks; }
}
