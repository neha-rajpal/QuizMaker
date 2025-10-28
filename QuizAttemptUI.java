package ui;

import model.Question;
import model.Quiz;
import model.Result;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuizAttemptUI extends JFrame {
    private int index = 0, score = 0, correct = 0, incorrect = 0;
    private Quiz selectedQuiz;
    private ArrayList<Quiz> quizzes;
    private ButtonGroup group;
    private JRadioButton[] options;
    private JLabel questionLabel;
    private JButton nextBtn;

    public QuizAttemptUI() {
        quizzes = FileHandler.loadAllQuizzes();
        if (quizzes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No quizzes found!");
            dispose();
            return;
        }

        String[] quizTitles = quizzes.stream().map(Quiz::getTitle).toArray(String[]::new);
        String chosen = (String) JOptionPane.showInputDialog(this, "Select Quiz:", "Quiz List",
                JOptionPane.PLAIN_MESSAGE, null, quizTitles, quizTitles[0]);
        if (chosen == null) { dispose(); return; }

        selectedQuiz = quizzes.stream().filter(q -> q.getTitle().equals(chosen)).findFirst().orElse(null);
        setupUI();
    }

    private void setupUI() {
        setTitle("Attempt Quiz - " + selectedQuiz.getTitle());
        setSize(500, 350);
        setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel);

        group = new ButtonGroup();
        options = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            add(options[i]);
        }

        nextBtn = new JButton("Next");
        add(nextBtn);

        nextBtn.addActionListener(e -> handleNext());
        loadQuestion();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadQuestion() {
        Question q = selectedQuiz.getQuestions().get(index);
        questionLabel.setText("Q" + (index + 1) + ": " + q.getQuestionText());
        String[] opts = q.getOptions();
        for (int i = 0; i < opts.length; i++) options[i].setText(opts[i]);
        group.clearSelection();
    }

    private void handleNext() {
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) selected = i;
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Select an answer!");
            return;
        }

        Question q = selectedQuiz.getQuestions().get(index);
        if (selected == q.getCorrectAnswer()) {
            score += q.getMarks();
            correct++;
        } else incorrect++;

        index++;
        if (index < selectedQuiz.getQuestions().size()) loadQuestion();
        else finishQuiz();
    }

    private void finishQuiz() {
        String name = JOptionPane.showInputDialog(this, "Enter your name:");
        double percent = (double) correct / selectedQuiz.getQuestions().size() * 100;
        Result result = new Result(name, selectedQuiz.getTitle(), score, correct, incorrect, percent);
        FileHandler.saveResult(result);
        JOptionPane.showMessageDialog(this, "Quiz Finished! Score: " + score);
        dispose();
    }
}
