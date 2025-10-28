package model;
import java.io.Serializable;

public class Result implements Serializable {
    private String userName;
    private String quizTitle;
    private int totalScore;
    private int correct;
    private int incorrect;
    private double percentage;

    public Result(String userName, String quizTitle, int totalScore, int correct, int incorrect, double percentage) {
        this.userName = userName;
        this.quizTitle = quizTitle;
        this.totalScore = totalScore;
        this.correct = correct;
        this.incorrect = incorrect;
        this.percentage = percentage;
    }

    public String getUserName() { return userName; }
    public String getQuizTitle() { return quizTitle; }
    public int getTotalScore() { return totalScore; }
    public int getCorrect() { return correct; }
    public int getIncorrect() { return incorrect; }
    public double getPercentage() { return percentage; }
}
