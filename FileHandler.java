package util;

import model.Quiz;
import model.Result;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private static final String QUIZ_FILE = "quizzes.dat";
    private static final String RESULT_FILE = "results.dat";

    public static void saveQuiz(Quiz quiz) {
        ArrayList<Quiz> quizzes = loadAllQuizzes();
        quizzes.add(quiz);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(QUIZ_FILE))) {
            out.writeObject(quizzes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Quiz> loadAllQuizzes() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(QUIZ_FILE))) {
            return (ArrayList<Quiz>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void saveResult(Result result) {
        ArrayList<Result> results = loadAllResults();
        results.add(result);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(RESULT_FILE))) {
            out.writeObject(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Result> loadAllResults() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(RESULT_FILE))) {
            return (ArrayList<Result>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
