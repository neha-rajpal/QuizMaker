package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private String title;
    private String description;
    private ArrayList<Question> questions = new ArrayList<>();

    public Quiz(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public ArrayList<Question> getQuestions() { return questions; }

    public void addQuestion(Question q) {
        questions.add(q);
    }
}
